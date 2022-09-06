package com.ou.foodie.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubCategorysVo {
    private String name;
    List<ThridSubCategorysVo> thridSubCategorysVoList=new ArrayList<>();


}
