package com.ou.foodie.configuration;

import com.ou.foodie.properties.ProjectProperties;
import com.ou.foodie.properties.RememberMe;
import lombok.AllArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RememberMeAuthenticationConfigure{
    private RememberMe rememberMe;
    private PersistentTokenRepository tokenRepository;
    private UserDetailsService userDetailsService;

    public void configure(HttpSecurity http) throws Exception {
        http.rememberMe().rememberMeCookieDomain(rememberMe.getRememberMeCookieDomain()).
                rememberMeCookieName(rememberMe.getRememberMeCookieName()).
                rememberMeParameter(rememberMe.getRememberMeParameter()).
                alwaysRemember(rememberMe.getAlwaysRemember()).
                tokenRepository(tokenRepository).
                userDetailsService(userDetailsService).
                tokenValiditySeconds(100);
    }
}
