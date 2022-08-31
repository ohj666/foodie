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
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class SessionAuthenticationConfigure extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private InvalidSessionStrategy invalidSessionStrategy;

    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    private ProjectProperties properties;
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .invalidSessionStrategy(invalidSessionStrategy)
                .maximumSessions(properties.getSession().getMaximumSessions())
                .maxSessionsPreventsLogin(properties.getSession().getMaxSessionsPreventsLogin())
                .expiredSessionStrategy(sessionInformationExpiredStrategy);
    }
}
