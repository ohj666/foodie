package com.ou.foodie.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.ou.foodie.properties.ProjectProperties;
import com.ou.foodie.properties.RememberMe;
import com.ou.foodie.vaildate.generator.DefaultImageValidateCodeGenerator;
import com.ou.foodie.vaildate.generator.DefaultMobileValidateCodeGenerator;
import com.ou.foodie.vaildate.generator.ImageValidateCodeGenerator;
import com.ou.foodie.vaildate.generator.MobileValidateCodeGenerator;
import com.ou.foodie.vaildate.sender.DefaultSmsCodeSender;
import com.ou.foodie.vaildate.sender.SmsCodeSender;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.social.connect.*;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.connect.web.SessionStrategy;

import javax.sql.DataSource;
import java.util.HashMap;

@AllArgsConstructor
@Configuration
public class ProjectBeanConfiguration {
    private ProjectProperties properties;
    private UsersConnectionRepository connectionRepository;

    private DataSource dataSource;

    @Bean
    public HashMap<String,String> mobileValidCode(){
        return new HashMap<String,String>();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ObjectMapper objectMapper(){
        return  new ObjectMapper();
    }

    @Bean
    public RequestCache requestCache(){
        return new HttpSessionRequestCache();
    }

    @Bean
    @ConditionalOnMissingBean(UserDetailsService.class)
    public UserDetailsService defaultUserDetailServiceImpl(){
        return new DefaultUserDetailServiceImpl();
    }

    @Bean
    public RedirectStrategy redirectStrategy(){
        return new DefaultRedirectStrategy();
    }

    @Bean
    public SessionStrategy sessionStrategy(){
        return new HttpSessionSessionStrategy();
    }

    @Bean
    @ConditionalOnMissingBean(ImageValidateCodeGenerator.class)
    public ImageValidateCodeGenerator imageValidateCodeGenerator(){
        DefaultImageValidateCodeGenerator defaultImageValidateCodeGenerator = new DefaultImageValidateCodeGenerator();
        defaultImageValidateCodeGenerator.setProperties(properties);
        return  defaultImageValidateCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(MobileValidateCodeGenerator.class)
    public MobileValidateCodeGenerator mobileValidateCodeGenerator(){
        DefaultMobileValidateCodeGenerator mobileValidateCodeGenerator=new DefaultMobileValidateCodeGenerator();
        mobileValidateCodeGenerator.setProperties(properties);

        return  mobileValidateCodeGenerator;
    }


    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender codeSender()
    {
        DefaultSmsCodeSender defaultSmsCodeSender = new DefaultSmsCodeSender();
        defaultSmsCodeSender.setProperties(properties);
        return defaultSmsCodeSender;
    }

    @Bean
    public PersistentTokenRepository tokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository=new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Bean
    public RememberMe rememberMe(){
        return new RememberMe();
    }

    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator){
        return new ProviderSignInUtils(connectionFactoryLocator,connectionRepository);
    }






}
