package com.ou.foodie.authorize;

import com.ou.foodie.properties.FormLoginProperties;
import com.ou.foodie.properties.ProjectConstant;
import com.ou.foodie.properties.ProjectProperties;
import lombok.AllArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;



@Component
@AllArgsConstructor
public class DefaultAuthorizeProvider implements AuthorizeProvider{
    private ProjectProperties properties;





    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        FormLoginProperties formLogin = properties.getFormLogin();
        ArrayList<String> staticPermitUrl = properties.getStaticPermitUrl();
        String[] strings = staticPermitUrl.toArray(new String[staticPermitUrl.size()]);
        config.antMatchers(strings)
                .permitAll()
                .antMatchers(formLogin.getLoginPage(),
                        ProjectConstant.REQUEST_AUTHENTICATION_URL,
                        ProjectConstant.REQUEST_VALIDATE_CODE+"/*",
                        properties.getSignUpUrl(),
                        ProjectConstant.REQUIRE_URL_REGISTER)
                .permitAll();


        return false;
    }
}
