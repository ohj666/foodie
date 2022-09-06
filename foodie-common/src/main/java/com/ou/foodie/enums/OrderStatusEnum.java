package com.ou.foodie.enums;

public enum OrderStatusEnum {
    WAIT_PAY(10,"待付款")
    ,WAIT_DELIVER(20,"已付款，代发货")
    ,WAIT_RECEIVE(30,"已发货，待收货")
    ,SUCCESS(40,"交易成功")
    ,CLOSE(50,"交易关闭");


    OrderStatusEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    };
    public final Integer type;
    public final String value;



}
