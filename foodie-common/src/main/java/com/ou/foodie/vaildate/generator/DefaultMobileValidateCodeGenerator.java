package com.ou.foodie.vaildate.generator;

import com.ou.foodie.properties.ProjectProperties;
import com.ou.foodie.vaildate.code.ValidateCode;
import lombok.Data;
import org.apache.commons.lang.RandomStringUtils;
@Data
public class DefaultMobileValidateCodeGenerator implements MobileValidateCodeGenerator{
    private ProjectProperties properties;

    @Override
    public ValidateCode generator() {
        return new ValidateCode(RandomStringUtils.randomNumeric(properties.getValidateCode().getSmsValidateCode().getLength()),properties.getValidateCode().getSmsValidateCode().getExpireIn());
    }
}
