package com.ou.foodie.vaildate.processor;

import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

public interface ValidateCodeProcessor {
    void createValidateCode(ServletWebRequest request) throws IOException;
    void validate(ServletWebRequest request);

    String getCodeType();

    Class getValidateCodeType();

    Boolean isNeedValida(ServletWebRequest request);


}
