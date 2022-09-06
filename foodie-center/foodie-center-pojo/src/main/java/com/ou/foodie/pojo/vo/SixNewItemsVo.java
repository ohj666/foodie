package com.ou.foodie.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Data
public class SixNewItemsVo implements Serializable {
    private String rootCatName;
    private String slogan;
    private String catImage;
    private String bgColor;
    private List<SimpleItemVo> simpleItemList=new ArrayList<>();
}
