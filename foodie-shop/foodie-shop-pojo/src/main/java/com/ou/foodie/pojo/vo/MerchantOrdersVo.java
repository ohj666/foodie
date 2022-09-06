package com.ou.foodie.pojo.vo;

import lombok.Data;

@Data
public class MerchantOrdersVo {
    private String merchantOrderId;
    private String userId;
    private Integer amount;
    private String returnUrl;
    private Integer payMethod;
}
