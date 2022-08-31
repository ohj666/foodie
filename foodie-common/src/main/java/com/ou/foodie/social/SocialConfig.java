package com.ou.foodie.social;

import com.ou.foodie.properties.ProjectProperties;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;
@Configuration
@EnableSocial
@AllArgsConstructor
@Order(1)
public class SocialConfig extends SocialConfigurerAdapter {
    private DataSource dataSource;
    private ProjectProperties properties;
    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
       /* CustomJdbcUsersConnectionRepository customJdbcUsersConnectionRepository = new CustomJdbcUsersConnectionRepository(dataSource,
                connectionFactoryLocator
                , Encryptors.noOpText());*/
        /**
         * 给Userconnection表添加前缀
         */
//        jdbcUsersConnectionRepository.setTablePrefix("user_");
//        return customJdbcUsersConnectionRepository;
        CustomUserConnectionRepository customUserConnectionRepository = new CustomUserConnectionRepository(dataSource,
                connectionFactoryLocator, Encryptors.noOpText());
        return customUserConnectionRepository;
    }


    @Bean
    public SpringSocialConfigurer socialAuthenticationConfig() {
        SocialAuthenticationConfig configurer = new SocialAuthenticationConfig(properties.getSocialProcessingUrl());
        configurer.signupUrl(properties.getSignUpUrl());
        return configurer;
    }
}




