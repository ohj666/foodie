package com.ou.foodie.security.configuration.token;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@AllArgsConstructor
@Configuration
public class RedisTokenStoreConfig {
    private RedisConnectionFactory connectionFactory;

    @Bean
    @ConditionalOnProperty(prefix = "com.ou.oauth2",
    name="storeType" , havingValue = "redis")
    public TokenStore redisTokenStore(){
        return new RedisTokenStore(connectionFactory);
    }
}
