package com.ou.foodie.pojo.vo;

import lombok.Data;
import org.omg.PortableInterceptor.INACTIVE;

import java.io.Serializable;

@Data
public class MySubOrderItemVo implements Serializable {
    private String itemId;
    private String itemName;
    private String  itemImg;
    private Integer buyCounts;
    private Integer  price;
    private String itemSpecId;
    private String itemSpecName;

}
