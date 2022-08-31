package com.ou.foodie.vaildate.processor;

import com.ou.foodie.exception.ValidateCodeException;
import com.ou.foodie.properties.ProjectConstant;
import com.ou.foodie.properties.ProjectProperties;
import com.ou.foodie.vaildate.code.ValidateCode;
import com.ou.foodie.vaildate.generator.ValidateCodeGenerator;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;
import java.util.List;

public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor{
    @Autowired
    private List<ValidateCodeGenerator> validateCodeGenerators;

    @Autowired
    private SessionStrategy sessionStrategy;
    @Autowired
    private ProjectProperties properties;

    public abstract void init() throws IOException;

    @Override
    public void createValidateCode(ServletWebRequest request) throws IOException {
        C generator=generator();
        save(generator,request);
        send(generator,request);
    }
    public C generator(){
        C validateCode=null;
        for (ValidateCodeGenerator validateCodeGenerator : validateCodeGenerators) {
            if(getValidateCodeType().isAssignableFrom(validateCodeGenerator.getClass())){
                validateCode= (C) validateCodeGenerator.generator();
            }
        }
        return validateCode;
    }

    @Override
    public void validate(ServletWebRequest request) {

            String validateCodeInRequest = request.getRequest().getParameter(properties.getValidateCode().getValidateCodeParmName());
            ValidateCode validateCodeInSession = (ValidateCode) sessionStrategy.getAttribute(request, ProjectConstant.VALIDATE_CODE_IN_SESSION);
            if(StringUtils.isBlank(validateCodeInRequest)){
                throw new ValidateCodeException("请求中未包含验证码");
            }
            if(validateCodeInSession==null){
                throw new ValidateCodeException("验证码还未生效");
            }
            if(StringUtils.isBlank(validateCodeInSession.getCode())){
                throw new ValidateCodeException("验证码还未生成");
            }
            if (!StringUtils.equals(validateCodeInRequest,validateCodeInSession.getCode())){
                throw new ValidateCodeException("验证码不匹配");
            }
            if(validateCodeInSession.isExpired()){
                throw new ValidateCodeException("验证码过期");
            }
            sessionStrategy.removeAttribute(request, ProjectConstant.VALIDATE_CODE_IN_SESSION);

    }
    public void save(C validateCode,ServletWebRequest request){
        sessionStrategy.setAttribute(request, ProjectConstant.VALIDATE_CODE_IN_SESSION,validateCode);
    }

    public void send(C validateCode,ServletWebRequest request) throws IOException {

    }


    @Override
    public String getCodeType() {
        return null;
    }


}
