package com.ou.foodie.pojo.bo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrdersBO {
    private String userId;
    private String itemSpecIds;
    private String addressId;
    private Integer payMethod;
    private String leftMsg;



}
