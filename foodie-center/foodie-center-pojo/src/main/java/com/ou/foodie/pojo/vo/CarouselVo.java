package com.ou.foodie.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarouselVo {
    private String type;
    private String catId;
    private String itemId;
    private String backgroundColor;
    private String  imageUrl;
}
