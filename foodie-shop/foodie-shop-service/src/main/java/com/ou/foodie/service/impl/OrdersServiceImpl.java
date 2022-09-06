package com.ou.foodie.service.impl;

import com.ou.foodie.enums.OrderStatusEnum;
import com.ou.foodie.enums.YesOrNo;
import com.ou.foodie.mapper.*;
import com.ou.foodie.pojo.*;
import com.ou.foodie.pojo.bo.OrdersBO;
import com.ou.foodie.pojo.vo.MerchantOrdersVo;
import com.ou.foodie.properties.CustomProperties;
import com.ou.foodie.properties.ProjectConstant;
import com.ou.foodie.properties.ProjectProperties;
import com.ou.foodie.service.AddressService;
import com.ou.foodie.service.ItemsService;
import com.ou.foodie.service.OrdersService;
import com.ou.foodie.util.DateUtils;
import com.ou.foodie.util.JsonResult;
import com.ou.foodie.util.SidUtils;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import sun.nio.cs.ext.MacCentralEurope;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class OrdersServiceImpl implements OrdersService {


    private OrdersMapper ordersMapper;
    private OrderStatusMapper orderStatusMapper;
    private OrderItemsMapper orderItemsMapper;

    private AddressService addressService;
    private ItemsService itemsService;
    private ItemsMapper itemsMapper;
    private RestTemplate restTemplate;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public JsonResult create(OrdersBO ordersBO) {
        String addressId = ordersBO.getAddressId();
        Integer payMethod = ordersBO.getPayMethod();
        String itemSpecIds = ordersBO.getItemSpecIds();
        String userId = ordersBO.getUserId();
        if(StringUtils.isBlank(addressId)||
        StringUtils.isBlank(itemSpecIds)||
        StringUtils.isBlank(userId)||
        payMethod==null){
            return JsonResult.error(500,"信息不完整");
        }
        String OrderId=SidUtils.get();
        //未设置邮费
        Integer PostAmount =0;
        //TODO 等待使用Redis时刷新后端的同步数据来更新buyCounts

        Integer buyCounts=1;
        String[] SpecIdS = ordersBO.getItemSpecIds().split(",");
        int total=0;
        int realTotal=0;
        for (String specId : SpecIdS) {

            ItemsSpec itemsSpec = itemsService.selectById(specId);


            OrderItems orderItems = new OrderItems();
            orderItems.setItemId(itemsSpec.getItemId());
            Items items = new Items();
            items.setId(itemsSpec.getItemId());
            Items items1 = itemsMapper.selectOne(items);
            ItemsImg itemsImg = itemsService.selectMainImgById(items1.getId());
            String url = itemsImg.getUrl();
            orderItems.setItemName(items1.getItemName());
            orderItems.setOrderId(OrderId);
            orderItems.setId(SidUtils.get());
            orderItems.setBuyCounts(buyCounts);
            orderItems.setItemSpecName(itemsSpec.getName());
            orderItems.setItemSpecId(itemsSpec.getId());

            int orderTotal=buyCounts*itemsSpec.getPriceDiscount();
            int orderNorTotal=buyCounts*itemsSpec.getPriceNormal();
            itemsService.decreaseItemSpectStock(itemsSpec.getId(),buyCounts);

            orderItems.setPrice(orderTotal);
            orderItems.setItemImg(url);
            orderItemsMapper.insert(orderItems);
            total+=orderNorTotal;
            realTotal+=orderTotal;

        }
        //获取



        Orders orders = new Orders();
        orders.setId(OrderId);
        orders.setUserId(ordersBO.getUserId());

        orders.setCreatedTime(new Date());
        orders.setIsDelete(YesOrNo.NO.type);
        orders.setLeftMsg(ordersBO.getLeftMsg());
        orders.setPayMethod(ordersBO.getPayMethod());
        orders.setIsComment(YesOrNo.NO.type);
        orders.setPostAmount(PostAmount);
        UserAddress userAddress1 = addressService.selectByIdAndUseId(addressId, userId);
        orders.setReceiverName(userAddress1.getReceiver());
        orders.setRealPayAmount(total);
        orders.setTotalAmount(realTotal);
        orders.setReceiverMobile(userAddress1.getMobile());
        orders.setReceiverAddress(userAddress1.getProvince()+userAddress1.getCity()+userAddress1.getDistrict()+userAddress1.getDetail());
        orders.setUpdatedTime(new Date());
        ordersMapper.insert(orders);
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(OrderId);
        orderStatus.setOrderStatus(OrderStatusEnum.WAIT_PAY.type);
        orderStatus.setCreatedTime(new Date());
        orderStatusMapper.insert(orderStatus);
        //TODO 同步更新cookie，把购物车已经购买的商品给删除掉

        MerchantOrdersVo merchantOrdersVo=new MerchantOrdersVo();
        merchantOrdersVo.setMerchantOrderId(OrderId);
        merchantOrdersVo.setUserId(ordersBO.getUserId());
        merchantOrdersVo.setPayMethod(ordersBO.getPayMethod());
        merchantOrdersVo.setAmount(realTotal+PostAmount);
        merchantOrdersVo.setReturnUrl(ProjectConstant.RETURN_URL);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("imoocUserId","imooc");
        httpHeaders.add("password","imooc");
        HttpEntity<MerchantOrdersVo> entity=new HttpEntity<>(merchantOrdersVo,httpHeaders);
        ResponseEntity<JsonResult> jsonResultResponseEntity = restTemplate.postForEntity(ProjectConstant.PAYMENTSERVERURL, entity, JsonResult.class);
        JsonResult body = jsonResultResponseEntity.getBody();
        if(body.getStatus()!=200){
            return JsonResult.error(500,"创建订单失败");
        }


        return JsonResult.isOk(OrderId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public JsonResult changeOrderStatus(String id, Integer orderStatus) {
        OrderStatus orderStatus1 = new OrderStatus();
        orderStatus1.setOrderId(id);
        orderStatus1.setOrderStatus(orderStatus);
        orderStatus1.setPayTime(new Date());
        orderStatusMapper.updateByPrimaryKeySelective(orderStatus1);
        return JsonResult.isOk();
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void closeOrderStatus() {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderStatus(OrderStatusEnum.WAIT_PAY.type);
        List<OrderStatus> select = orderStatusMapper.select(orderStatus);
        for (OrderStatus status : select) {

            Date createdTime = status.getCreatedTime();
            String s = DateUtils.dateMinus(new Date(),createdTime);
            float v = Float.parseFloat(s);
            if(v>=1.0){
                status.setOrderId(status.getOrderId());
                status.setOrderStatus(OrderStatusEnum.CLOSE.type);
                status.setCloseTime(new Date());
                orderStatusMapper.updateByPrimaryKeySelective(status);
            }

        }

    }




}
