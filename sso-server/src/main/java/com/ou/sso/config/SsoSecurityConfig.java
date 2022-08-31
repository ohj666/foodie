package com.ou.sso.config;

import com.ou.foodie.authorize.AuthorizeManager;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
public class SsoSecurityConfig extends WebSecurityConfigurerAdapter {



    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.requestMatchers().anyRequest().and().authorizeRequests().antMatchers("/hi").authenticated();
//        http.requestMatchers().anyRequest().and().authorizeRequests().antMatchers("/hi").authenticated();
//        http.requestMatchers().anyRequest().and().authorizeRequests().antMatchers("/test/*").authenticated();
        http.formLogin();
        http.authorizeRequests().antMatchers("/test/**").hasRole("USER").and().authorizeRequests().anyRequest().authenticated();

//        http.authorizeRequests().antMatchers("/test/**").permitAll();

//        http.formLogin().and().authorizeRequests().antMatchers().authenticated().and().authorizeRequests().antMatchers("/hi").hasRole("admin");

////
//        http.authorizeRequests()
//                .antMatchers(
//                        "/login.html",
//                        "/authentication/require",
//                        "/code/image",
//                        "/session/invalid",
//                        "/logout.html"
//                ).permitAll()
//                .antMatchers("/user").hasRole("ADMIN")
//                .anyRequest()
//                .authenticated();
//        http.csrf().disable();

//        http.requestMatchers().anyRequest().and().authorizeRequests().antMatchers("/test/*").authenticated();
//        http.csrf().disable();

    }
}
