package com.ou.foodie.web.controller;

import com.ou.foodie.mapper.OrdersMapper;
import com.ou.foodie.server.AddressService;
import com.ou.foodie.server.OrdersService;
import com.ou.foodie.util.JsonResult;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/myorders")
@RestController
@AllArgsConstructor
public class OrdersController {
    private OrdersService ordersService;

    @PostMapping("/query")
    public JsonResult AddressList(String userId, String orderStatus, @RequestParam(required = false ,defaultValue = "1") Integer page,@RequestParam(required = false ,defaultValue = "5") Integer pageSize){
        return ordersService.selectByIdOrders(userId,orderStatus,page,pageSize);
    }

    @GetMapping("/driver")
    public JsonResult driverOrder(String orderId){
        return ordersService.driverOrder(orderId);
    }

    @PostMapping("/confirmReceive")
    public JsonResult confirmReceive(String userId,String orderId){
        return ordersService.confirmReceive(userId,orderId);
    }



    @PostMapping("/delete")
    public JsonResult delete(String userId,String orderId){
        return ordersService.delete(userId,orderId);
    }

    @PostMapping("/statusCounts")
    public JsonResult statusCounts(String userId){
        return ordersService.orderStatusCounts(userId);
    }

    @PostMapping("/trend")
    public JsonResult trend(String userId,@RequestParam(required = false , defaultValue = "1") Integer page,@RequestParam(required = false , defaultValue = "10") Integer pageSize){
        return ordersService.trend(userId,page,pageSize);
    }



}
