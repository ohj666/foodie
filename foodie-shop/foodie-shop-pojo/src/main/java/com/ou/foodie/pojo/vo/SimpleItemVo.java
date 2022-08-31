package com.ou.foodie.pojo.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class SimpleItemVo implements Serializable {
    private String itemName;
    private String itemId;
    private String itemUrl;


}
