package com.ou.foodie.configuration;

import com.ou.foodie.properties.FormLoginProperties;
import com.ou.foodie.properties.ProjectConstant;
import com.ou.foodie.properties.ProjectProperties;
import lombok.AllArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class FormAuthenticationConfigure{
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    private AuthenticationFailureHandler authenticationFailureHandler;
    private ProjectProperties properties;
    public void configure(HttpSecurity http) throws Exception {
        FormLoginProperties formLogin = properties.getFormLogin();
        http.formLogin().loginPage(ProjectConstant.REQUEST_AUTHENTICATION_URL).loginProcessingUrl(formLogin.getLoginProcessingUrl())
                .usernameParameter(formLogin.getUsernameParam())
                .passwordParameter(formLogin.getPasswordParam())
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler);
    }
}
