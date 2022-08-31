package com.ou.foodie.vaildate.processor;


import com.ou.foodie.properties.ProjectConstant;
import com.ou.foodie.properties.ProjectProperties;
import com.ou.foodie.vaildate.code.ValidateCode;
import com.ou.foodie.vaildate.generator.MobileValidateCodeGenerator;
import com.ou.foodie.vaildate.sender.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class MobileValidateCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {
    @Autowired
    private ProjectProperties properties;
    @Autowired
    private SmsCodeSender smsCodeSender;

    Set<String> processingUrlSet = new HashSet<>();

    @Override
    public String getCodeType() {
        return properties.getValidateCode().getSmsValidateCode().getCodeType();
    }

    @Override
    public Class getValidateCodeType() {
        return MobileValidateCodeGenerator.class;
    }

    @Override
    public Boolean isNeedValida(ServletWebRequest request) {
        return processingUrlSet.contains(request.getRequest().getRequestURI());
    }

    @Override
    public void send(ValidateCode validateCode, ServletWebRequest request) throws IOException {
        smsCodeSender.send(request, validateCode);
    }


    @Override
    @PostConstruct
    public void init() throws IOException {
        List<String> processingUrl = properties.getValidateCode().getSmsValidateCode().getProcessingUrl();
        for (String s : processingUrl) {
            processingUrlSet.add(s);

        }
        processingUrlSet.add(ProjectConstant.MOBILE_AUTHENTICATION_PROCESSING_URL);
    }
}


