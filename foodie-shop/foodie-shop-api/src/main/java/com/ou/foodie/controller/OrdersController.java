package com.ou.foodie.controller;

import com.ou.foodie.pojo.bo.OrdersBO;
import com.ou.foodie.service.OrdersService;
import com.ou.foodie.util.JsonResult;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrdersController {


    private OrdersService ordersService;

    @PostMapping("/create")
    public JsonResult create(@RequestBody OrdersBO ordersBO){
        System.out.println(ordersBO);
        return ordersService.create(ordersBO);
    }
}
