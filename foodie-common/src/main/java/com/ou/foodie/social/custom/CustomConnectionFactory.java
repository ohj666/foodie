package com.ou.foodie.social.custom;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;

public class CustomConnectionFactory extends OAuth2ConnectionFactory<Custom> {
    public CustomConnectionFactory(String providerId, String appid,String appSecret) {
        super(providerId, new CustomServiceProvider(appid,appSecret), new CustomAdapter());
    }
}
