package com.ou.foodie.security.configuration.web.security.configuration;

import com.ou.foodie.authorize.AuthorizeManager;
import com.ou.foodie.configuration.CSRFAuthenticationConfigure;
import com.ou.foodie.configuration.FormAuthenticationConfigure;
import com.ou.foodie.configuration.RememberMeAuthenticationConfigure;
import com.ou.foodie.configuration.SessionAuthenticationConfigure;
import com.ou.foodie.properties.FormLoginProperties;
import com.ou.foodie.properties.ProjectConstant;
import com.ou.foodie.properties.ProjectProperties;
import com.ou.foodie.properties.RememberMe;
import com.ou.foodie.vaildate.ValidateCodeFilter;
import com.ou.foodie.vaildate.authentication.MobileAuthenticationConfiguration;
import com.ou.foodie.vaildate.processor.ValidateCodeProcessor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.ExpiredAuthorizationException;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import java.util.ArrayList;
import java.util.List;

import static com.ou.foodie.properties.ProjectConstant.REQUIRE_URL_REGISTER;



@AllArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private MobileAuthenticationConfiguration mobileAuthenticationConfiguration;
    private SpringSocialConfigurer socialConfigurerAdapter;
    private FormAuthenticationConfigure authenticationConfigure;

    private SessionAuthenticationConfigure sessionAuthenticationConfigure;

    private CSRFAuthenticationConfigure csrfAuthenticationConfigure;
    private RememberMeAuthenticationConfigure rememberMeAuthenticationConfigure;
    private AuthorizeManager authorizeManager;
    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        ValidateCodeFilter validateCodeFilter=new ValidateCodeFilter();
//        validateCodeFilter.setValidateCodeProcessors(validateCodeProcessors);
//        validateCodeFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
//        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class);
//        http.apply(socialConfigurerAdapter);
        http.apply(mobileAuthenticationConfiguration);
        authenticationConfigure.configure(http);
        http.apply(sessionAuthenticationConfigure);
        rememberMeAuthenticationConfigure.configure(http);
        csrfAuthenticationConfigure.configure(http);
        authorizeManager.config(http.authorizeRequests());
    }
}
