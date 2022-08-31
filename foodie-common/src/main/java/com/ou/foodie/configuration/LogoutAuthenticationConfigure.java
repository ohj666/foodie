package com.ou.foodie.configuration;

import com.ou.foodie.properties.FormLoginProperties;
import com.ou.foodie.properties.ProjectProperties;
import lombok.AllArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class LogoutAuthenticationConfigure extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private LogoutSuccessHandler logoutSuccessHandler;
    private ProjectProperties properties;
    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.logout().logoutUrl(properties.getLogout()).logoutSuccessHandler(logoutSuccessHandler).deleteCookies("JSESSIONID");
    }
}
