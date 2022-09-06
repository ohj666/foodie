package com.ou.foodie.properties;

import com.ou.foodie.session.Session;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Data
@Configuration
@ConfigurationProperties("com.ou")
public class ProjectProperties {

    private  String socialProcessingUrl="/social";
    private String signUpUrl="/register.html";
    private String logout="/MyLogout";
    private Session session=new Session();
    private List<String> allowedOrigins=new ArrayList<String>();
    FormLoginProperties formLogin=new FormLoginProperties();
    private ArrayList<String> staticPermitUrl=new ArrayList<String>();
    ValidateCodeProperties validateCode=new ValidateCodeProperties();
    QQProperties qq=new QQProperties();
    private CustomProperties custom=new CustomProperties();
    private Oauth2Properties oauth2;
    private String imgFileUri;
    private String imgFileUrl;


}
