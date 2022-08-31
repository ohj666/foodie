package com.ou.foodie.authorize;

import lombok.AllArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DefaultAuthorizeManger implements AuthorizeManager{

    private List<AuthorizeProvider> authorizeProviders;
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        boolean existAnyRequest=false;
        String existAnyRequestConfigName=null;
        for (AuthorizeProvider authorizeProvider : authorizeProviders) {
            boolean currentAnyRequestConfig= authorizeProvider.config(config);
            if(existAnyRequest && currentAnyRequestConfig){
                throw new RuntimeException("重复的anyrequest 的配置 : " + authorizeProvider.getClass().getSimpleName() + " 和 " + existAnyRequestConfigName);
            }else if(currentAnyRequestConfig){
                existAnyRequest=true;
                existAnyRequestConfigName=authorizeProvider.getClass().getSimpleName();
            }

            if (!existAnyRequest){
                config.anyRequest().authenticated();
            }

        }


    }
}
