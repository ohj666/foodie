package com.ou.foodie.social.qq;

import com.ou.foodie.properties.ProjectProperties;
import com.ou.foodie.properties.QQProperties;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

@Configuration
@AllArgsConstructor
@ConditionalOnProperty(prefix = "com.ou.qq",name = "appid")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {
    protected ProjectProperties properties;


    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qq=properties.getQq();
        return new QQConnectionFactory(qq.getProviderId(),qq.getAppid(),qq.getAppsecret());
    }
}
