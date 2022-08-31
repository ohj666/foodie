package com.ou.foodie.social.qq;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;

public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {
    public QQConnectionFactory(String providerId, String appid,String appSecret) {
        super(providerId, new QQServiceProvider(appid,appSecret),new QQAdapter());
    }
}
