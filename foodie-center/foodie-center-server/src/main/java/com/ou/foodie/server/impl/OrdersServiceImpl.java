package com.ou.foodie.server.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ou.foodie.enums.OrderStatusEnum;
import com.ou.foodie.enums.YesOrNo;
import com.ou.foodie.mapper.OrderItemsMapper;
import com.ou.foodie.mapper.OrderStatusMapper;
import com.ou.foodie.mapper.OrdersMapper;
import com.ou.foodie.pojo.OrderItems;
import com.ou.foodie.pojo.OrderStatus;
import com.ou.foodie.pojo.Orders;
import com.ou.foodie.pojo.vo.MyOrdersVo;
import com.ou.foodie.pojo.vo.MySubOrderItemVo;
import com.ou.foodie.pojo.vo.OrderStatusCountsVo;
import com.ou.foodie.server.OrdersService;
import com.ou.foodie.util.JsonResult;
import com.ou.foodie.util.PagedGridResult;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.annotation.Order;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
@AllArgsConstructor
public class OrdersServiceImpl implements OrdersService {
    private OrdersMapper ordersMapper;
    private OrderStatusMapper orderStatusMapper;
    private OrderItemsMapper orderItemsMapper;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public JsonResult selectByIdOrders(String userId, String orderStatus, Integer page, Integer pageSize) {
        Map<String,Object> map=new HashMap();
        Page<Object> pageObject = PageHelper.startPage(page, pageSize, true, null, false);
        map.put("userId",userId);
        if (!StringUtils.isBlank(orderStatus)){
            map.put("status",orderStatus);
        }
        List<MyOrdersVo> rows=ordersMapper.selectMyOrder(map);
        for (MyOrdersVo row : rows) {

            String orderId = row.getOrderId();
            OrderItems orderItems = new OrderItems();
            orderItems.setOrderId(orderId);
            List<OrderItems> select = orderItemsMapper.select(orderItems);
            List<MySubOrderItemVo> list=new ArrayList<>();
            for (OrderItems items : select) {
                MySubOrderItemVo mySubOrderItemVo = new MySubOrderItemVo();
                BeanUtils.copyProperties(items,mySubOrderItemVo);

                list.add(mySubOrderItemVo);
            }
            row.setSubOrderItemList(list);
        }
        PagedGridResult grid=new PagedGridResult();
        grid.setPage(pageObject.getPageNum());
        grid.setTotal(pageObject.getPages());
        grid.setRecords(pageObject.getTotal());
        grid.setRows(rows);
        return JsonResult.isOk(grid);

    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public JsonResult driverOrder(String orderId) {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setDeliverTime(new Date());
        orderStatus.setOrderStatus(OrderStatusEnum.WAIT_RECEIVE.type);
        Example example = new Example(OrderStatus.class);
        example.createCriteria().andEqualTo("orderId",orderId).andEqualTo("orderStatus",OrderStatusEnum.WAIT_DELIVER.type);
        orderStatusMapper.updateByExampleSelective(orderStatus,example);
        return JsonResult.isOk();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public JsonResult confirmReceive(String userId, String orderId) {
        if (verificationUser(userId)){
            return JsonResult.error(500,"获取不到用户信息");
        }
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderStatus(OrderStatusEnum.SUCCESS.type);
        orderStatus.setOrderId(orderId);
        orderStatus.setSuccessTime(new Date());
        Example example = new Example(OrderStatus.class);
        example.createCriteria().andEqualTo("orderId",orderId).andEqualTo("orderStatus",OrderStatusEnum.WAIT_RECEIVE.type);
        orderStatusMapper.updateByExampleSelective(orderStatus,example);
        return JsonResult.isOk();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public JsonResult delete(String userId, String orderId) {
        if (verificationUser(userId)){
            return JsonResult.error(500,"获取不到用户信息");
        }
        Orders orders = new Orders();
        orders.setUserId(userId);
        orders.setId(orderId);
        orders.setIsDelete(YesOrNo.YES.type);
        ordersMapper.updateByPrimaryKeySelective(orders);
        return JsonResult.isOk();

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public JsonResult orderStatusCounts(String userId) {
        Map<String,Object> map=new HashMap<>();
        map.put("userId",userId);
        map.put("status",OrderStatusEnum.WAIT_PAY.type);
        int waitPay=ordersMapper.getMyOrderStatusCounts(map);
        map.put("status",OrderStatusEnum.WAIT_DELIVER.type);
        int waitDeliver=ordersMapper.getMyOrderStatusCounts(map);
        map.put("status",OrderStatusEnum.WAIT_RECEIVE.type);
        int waitReceive=ordersMapper.getMyOrderStatusCounts(map);
        map.put("status",OrderStatusEnum.SUCCESS.type);
        map.put("isComment",YesOrNo.NO);
        int isComment=ordersMapper.getMyOrderStatusCounts(map);
        return JsonResult.isOk(new OrderStatusCountsVo(waitPay,waitDeliver,waitReceive,isComment));
    }

    @Override
    public JsonResult trend(String userId,Integer page, Integer pageSize) {
        Page<Object> pageObject = PageHelper.startPage(page, pageSize, true, null, false);
        List<OrderStatus> orderStatus=ordersMapper.getTrend(userId);
        PagedGridResult grid=new PagedGridResult();
        grid.setPage(pageObject.getPageNum());
        grid.setTotal(pageObject.getPages());
        grid.setRecords(pageObject.getTotal());
        grid.setRows(orderStatus);
        return JsonResult.isOk(grid);

    }


    public boolean verificationUser(String userId){

        Orders orders = new Orders();
        orders.setUserId(userId);
        List<Orders> select = ordersMapper.select(orders);
        if(select==null || select.size()==0){
            return true;
        }
        return false;
    }
}
