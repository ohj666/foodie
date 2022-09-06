package com.ou.foodie.service;

import com.ou.foodie.pojo.bo.OrdersBO;
import com.ou.foodie.util.JsonResult;

public interface OrdersService {
    JsonResult create(OrdersBO ordersBO);

    JsonResult changeOrderStatus(String id, Integer orderStatus);

    void closeOrderStatus();
}
