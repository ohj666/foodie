package com.ou.foodie.social.custom;

import com.ou.foodie.properties.CustomProperties;
import com.ou.foodie.properties.ProjectProperties;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

@Configuration
@AllArgsConstructor
@ConditionalOnProperty(prefix = "com.ou.custom",name = "appid")
public class CustomAutoConfig extends SocialAutoConfigurerAdapter {
    protected ProjectProperties properties;


    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        CustomProperties custom = properties.getCustom();
        return new CustomConnectionFactory(custom.getProviderId(),custom.getAppid(),custom.getAppsecret());
    }
}
