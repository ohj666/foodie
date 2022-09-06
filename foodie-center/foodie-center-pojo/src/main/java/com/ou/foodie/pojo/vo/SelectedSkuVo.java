package com.ou.foodie.pojo.vo;

import lombok.Data;

@Data
public class SelectedSkuVo {
    private String name;
    private String id;
    private Integer priceDiscount;
    private Integer priceNormal;
    private Integer stock;
    private Float discounts;
}
