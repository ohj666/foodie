package com.ou.foodie.properties;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SmsValidateCodeProperties {

    private Integer length=4;
    private Integer expireIn=15*60;
    private String codeType="sms";
    private List<String> processingUrl=new ArrayList<>();
}
