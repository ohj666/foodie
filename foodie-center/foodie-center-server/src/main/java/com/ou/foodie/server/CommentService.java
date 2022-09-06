package com.ou.foodie.server;

import com.ou.foodie.pojo.bo.OrderItemBo;
import com.ou.foodie.util.JsonResult;

import java.util.List;

public interface CommentService {



    JsonResult pendingComment(String userId,String orderId);

    JsonResult saveList(String userId, String orderId,List<OrderItemBo> orderItemList);

    JsonResult query(String userId, Integer page, Integer pageSize);
}
