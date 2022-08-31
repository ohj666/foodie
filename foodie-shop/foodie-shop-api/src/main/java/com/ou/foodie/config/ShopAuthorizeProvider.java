package com.ou.foodie.config;

import com.ou.foodie.authorize.AuthorizeProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ShopAuthorizeProvider implements AuthorizeProvider {
    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.anyRequest().permitAll();
        return true;
    }
}
