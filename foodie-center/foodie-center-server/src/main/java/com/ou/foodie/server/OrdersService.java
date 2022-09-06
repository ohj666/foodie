package com.ou.foodie.server;

import com.ou.foodie.util.JsonResult;

public interface OrdersService {
    JsonResult selectByIdOrders(String userId, String orderStatus, Integer page, Integer pageSize);


    JsonResult driverOrder(String orderId);

    JsonResult confirmReceive(String userId, String orderId);

    JsonResult delete(String userId, String orderId);
    JsonResult orderStatusCounts(String userId);

    JsonResult trend(String userId,Integer page, Integer pageSize);
}
