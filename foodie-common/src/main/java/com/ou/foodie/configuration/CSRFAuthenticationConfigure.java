package com.ou.foodie.configuration;

import com.ou.foodie.properties.RememberMe;
import lombok.AllArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CSRFAuthenticationConfigure{
    public void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
    }
}
