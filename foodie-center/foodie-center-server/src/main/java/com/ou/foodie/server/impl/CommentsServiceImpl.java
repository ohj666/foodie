package com.ou.foodie.server.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ou.foodie.enums.YesOrNo;
import com.ou.foodie.mapper.*;
import com.ou.foodie.pojo.*;
import com.ou.foodie.pojo.bo.OrderItemBo;
import com.ou.foodie.pojo.vo.HistoryCommentVo;
import com.ou.foodie.pojo.vo.OrderItemVo;
import com.ou.foodie.server.CommentService;
import com.ou.foodie.util.JsonResult;
import com.ou.foodie.util.PagedGridResult;
import com.ou.foodie.util.SidUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@AllArgsConstructor
public class CommentsServiceImpl implements CommentService {
    private OrderStatusMapper orderStatusMapper;
    private ItemsCommentsMapper itemsCommentsMapper;
    private OrdersMapper ordersMapper;
    private OrderItemsMapper orderItemsMapper;
    private ItemsImgMapper itemsImgMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public JsonResult pendingComment(String userId,String orderId) {
        if (verificationUser(userId,orderId)){
            JsonResult.error(500,"找不到此订单");
        }
        OrderItems orderItems = new OrderItems();
        orderItems.setOrderId(orderId);

        List<OrderItems> select = orderItemsMapper.select(orderItems);
        return JsonResult.isOk(select);

    }



    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public JsonResult saveList(String userId, String orderId, List<OrderItemBo> orderItemList) {
        if(verificationUser(userId,orderId)){
            return JsonResult.error(500,"用户信息不正确");
        }
        if (orderItemList==null || orderItemList.isEmpty()||orderItemList.size()==0){
            return JsonResult.error(500,"用户没有评价");
        }
        for (OrderItemBo orderItemBo : orderItemList) {
            orderItemBo.setCommentId(SidUtils.get());
        }
        Map<String,Object> map=new HashMap<>();
        map.put("userId",userId);
        map.put("commentList",orderItemList);
        itemsCommentsMapper.saveList(map);
        Orders orders = new Orders();
        orders.setId(orderId);
        orders.setUserId(userId);
        orders.setIsComment(YesOrNo.YES.type);
        ordersMapper.updateByPrimaryKeySelective(orders);

        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setCommentTime(new Date());

        orderStatusMapper.updateByPrimaryKeySelective(orderStatus);


        return JsonResult.isOk();
    }

    @Override
    public JsonResult query(String userId, Integer page, Integer pageSize) {
        ItemsComments itemsComments = new ItemsComments();
        Page<Object> page1 = PageHelper.startPage(page, pageSize, true, null, false);
        itemsComments.setUserId(userId);
        List<ItemsComments> select = itemsCommentsMapper.select(itemsComments);
        List<HistoryCommentVo> list=new ArrayList<>();
        for (ItemsComments comments : select) {
            ItemsImg itemsImg = new ItemsImg();
            itemsImg.setIsMain(1);
            itemsImg.setItemId(comments.getItemId());
            ItemsImg itemsImg1 = itemsImgMapper.selectOne(itemsImg);
            HistoryCommentVo historyCommentVo = new HistoryCommentVo();
            BeanUtils.copyProperties(comments,historyCommentVo);
            historyCommentVo.setItemImg(itemsImg1.getUrl());
            historyCommentVo.setSpecName(comments.getSepcName());
            list.add(historyCommentVo);
        }

        PagedGridResult grid=new PagedGridResult();
        grid.setPage(page1.getPageNum());
        grid.setTotal(page1.getPages());
        grid.setRecords(page1.getTotal());
        grid.setRows(list);



        return JsonResult.isOk(grid);
    }

    private  boolean verificationUser(String userId,String orderId){
        Orders orders = new Orders();
        orders.setUserId(userId);
        orders.setId(orderId);
        Orders orders1 = ordersMapper.selectOne(orders);
        return orders1==null;
    }
}
