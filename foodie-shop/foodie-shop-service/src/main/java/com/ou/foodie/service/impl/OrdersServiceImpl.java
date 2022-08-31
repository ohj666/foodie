package com.ou.foodie.service.impl;

import com.ou.foodie.pojo.bo.OrdersBO;
import com.ou.foodie.service.OrdersService;
import com.ou.foodie.util.JsonResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Override
    public JsonResult create(OrdersBO ordersBO) {
        String addressId = ordersBO.getAddressId();
        String payMethod = ordersBO.getPayMethod();
        String itemSpecIds = ordersBO.getItemSpecIds();
        String userId = ordersBO.getUserId();
        if(StringUtils.isBlank(addressId)||
        StringUtils.isBlank(payMethod)||
        StringUtils.isBlank(itemSpecIds)||
        StringUtils.isBlank(userId)){
            return JsonResult.error(500,"信息不完整");

        }

        return JsonResult.isOk();
    }
}
