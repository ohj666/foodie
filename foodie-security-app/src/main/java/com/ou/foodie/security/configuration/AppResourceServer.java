package com.ou.foodie.security.configuration;

import com.ou.foodie.authorize.AuthorizeManager;
import com.ou.foodie.configuration.CSRFAuthenticationConfigure;
import com.ou.foodie.configuration.FormAuthenticationConfigure;
import com.ou.foodie.configuration.RememberMeAuthenticationConfigure;
import com.ou.foodie.configuration.SessionAuthenticationConfigure;
import com.ou.foodie.properties.FormLoginProperties;
import com.ou.foodie.properties.ProjectConstant;
import com.ou.foodie.properties.ProjectProperties;
import com.ou.foodie.vaildate.ValidateCodeFilter;
import com.ou.foodie.vaildate.authentication.MobileAuthenticationConfiguration;
import com.ou.foodie.vaildate.processor.ValidateCodeProcessor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
@EnableResourceServer
@AllArgsConstructor
public class AppResourceServer extends ResourceServerConfigurerAdapter {
    private MobileAuthenticationConfiguration mobileAuthenticationConfiguration;
    private SpringSocialConfigurer socialConfigurerAdapter;
    private FormAuthenticationConfigure authenticationConfigure;

    private SessionAuthenticationConfigure sessionAuthenticationConfigure;

    private CSRFAuthenticationConfigure csrfAuthenticationConfigure;
    private RememberMeAuthenticationConfigure rememberMeAuthenticationConfigure;
    private AuthorizeManager authorizeManager;
    @Override
    public void configure(HttpSecurity http) throws Exception {

//        ValidateCodeFilter validateCodeFilter=new ValidateCodeFilter();
//        validateCodeFilter.setValidateCodeProcessors(validateCodeProcessors);
//        validateCodeFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
//        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class);
        http.apply(socialConfigurerAdapter);
        http.apply(mobileAuthenticationConfiguration);
        authenticationConfigure.configure(http);
        http.apply(sessionAuthenticationConfigure);
        rememberMeAuthenticationConfigure.configure(http);
        csrfAuthenticationConfigure.configure(http);
        authorizeManager.config(http.authorizeRequests());
    }
}
