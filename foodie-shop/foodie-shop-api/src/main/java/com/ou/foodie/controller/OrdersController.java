package com.ou.foodie.controller;

import com.ou.foodie.enums.OrderStatusEnum;
import com.ou.foodie.pojo.bo.OrdersBO;
import com.ou.foodie.service.OrdersService;
import com.ou.foodie.util.JsonResult;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrdersController {


    private OrdersService ordersService;


    @PostMapping("/create")
    public JsonResult create(@RequestBody OrdersBO ordersBO){

        return ordersService.create(ordersBO);
    }

    @PostMapping("/getPaidOrderInfo")
    public JsonResult getPaidOrderInfo(String orderId){
        return null;
    }


    @PostMapping("/notifyMerchantOrderPaid")
    public JsonResult notifyMerchantOrderPaid(String id,Integer orderStatus){
        return ordersService.changeOrderStatus(id,orderStatus);
    }
}
