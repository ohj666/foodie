package com.ou.foodie.pojo.bo;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class ShopCartBo {

    private String itemId;
    private String itemImgUrl;
    private String itemName;
    private String specId;
    private String specName;
    private String buyCounts;
    private String priceDiscount;
    private String priceNormal;


}
