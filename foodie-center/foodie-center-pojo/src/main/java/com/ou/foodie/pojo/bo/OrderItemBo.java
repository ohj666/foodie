package com.ou.foodie.pojo.bo;

import lombok.Data;

@Data
public class OrderItemBo {
    private String commentId;
    private String itemName;
    private String itemSpecId;
    private String itemSpecName;
    private String content;
    private String commentLevel;
    private String itemImg;
    private String id;
    private String itemId;
    private String price;
    private String orderId;
    private String buyCounts;

}
