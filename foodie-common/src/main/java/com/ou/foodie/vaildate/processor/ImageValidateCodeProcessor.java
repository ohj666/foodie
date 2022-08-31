package com.ou.foodie.vaildate.processor;

import com.ou.foodie.properties.ProjectProperties;
import com.ou.foodie.vaildate.code.ImageValidateCode;
import com.ou.foodie.vaildate.generator.ImageValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class ImageValidateCodeProcessor extends AbstractValidateCodeProcessor<ImageValidateCode> {
    @Autowired
    private ProjectProperties properties;


    private Set<String> processingUrlSet=new HashSet<>();
    @Override
    public String getCodeType() {
        return properties.getValidateCode().getImageValidateCode().getCodeType();
    }



    @Override
    public Class getValidateCodeType() {
        return ImageValidateCodeGenerator.class;
    }

    @Override
    public Boolean isNeedValida(ServletWebRequest request) {
        return processingUrlSet.contains(request.getRequest().getRequestURI());
    }

    @Override
    public void send(ImageValidateCode validateCode, ServletWebRequest request) throws IOException {
        ImageIO.write(validateCode.getBufferedImage(),"JPEG",request.getResponse().getOutputStream());
    }


    @Override
    @PostConstruct
    public void init() throws IOException {
        List<String> processingUrl = properties.getValidateCode().getImageValidateCode().getProcessingUrl();
        for (String s : processingUrl) {
            processingUrlSet.add(s);
        }
        processingUrlSet.add(properties.getFormLogin().getLoginProcessingUrl());

    }


}
