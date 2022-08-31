package com.ou.foodie.properties;

import lombok.Data;

@Data
public class ValidateCodeProperties {
    ImageValidateCodeProperties ImageValidateCode=new ImageValidateCodeProperties();
    SmsValidateCodeProperties smsValidateCode=new SmsValidateCodeProperties();
    private String validateCodeParmName="validateCode";

}
