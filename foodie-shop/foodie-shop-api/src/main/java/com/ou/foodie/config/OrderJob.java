package com.ou.foodie.config;

import com.ou.foodie.mapper.OrderStatusMapper;
import com.ou.foodie.service.OrdersService;
import com.ou.foodie.util.DateUtils;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

//@Component
@AllArgsConstructor
public class OrderJob {

    private OrdersService ordersService;

    @Scheduled(cron = "0/5 * * * * ? ")
    public void autoCloseOrder(){
        System.out.println("执行定时任务，当前时间为:"+ new Date());
        ordersService.closeOrderStatus();
    }
}
