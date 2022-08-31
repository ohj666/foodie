package com.ou.foodie.properties;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ImageValidateCodeProperties {
    private Integer height=32;
    private Integer width=64;
    private Integer length=4;
    private Integer expireIn=15*60;
    private String codeType="image";
    private List<String> processingUrl=new ArrayList<>();
}
