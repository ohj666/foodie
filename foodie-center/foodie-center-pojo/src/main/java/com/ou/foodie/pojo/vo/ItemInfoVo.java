package com.ou.foodie.pojo.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class ItemInfoVo {
    private ItemVo item;
    private ItemParamVo  itemParams;
    private List<SelectedSkuVo> itemSpecList=new ArrayList<>();
    private List<ItemImgVo> itemImgList=new ArrayList<>();
}
