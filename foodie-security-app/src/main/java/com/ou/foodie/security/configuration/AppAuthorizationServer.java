package com.ou.foodie.security.configuration;

import com.ou.foodie.properties.ClientProperties;
import com.ou.foodie.properties.ProjectProperties;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableAuthorizationServer

public class AppAuthorizationServer extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private ProjectProperties properties;
    @Autowired
    private  AuthenticationManager authenticationManager;
    @Autowired
    private  TokenStore tokenStore;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired(required = false)
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired(required = false)
    private TokenEnhancer tokenEnhancer;
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        InMemoryClientDetailsServiceBuilder inMemory = clients.inMemory();
        ClientProperties[] clientProperties = properties.getOauth2().getClient();
        for (ClientProperties client : clientProperties) {
            inMemory.withClient(client.getClientId())
                    .secret(client.getClientSecret())
                    .authorities(client.getAuthorities())
                    .resourceIds(client.getResourceIds())
                    .authorizedGrantTypes(client.getAuthorizedGrantTypes())
                    .redirectUris(client.getRedirectUris())
                    .scopes(client.getScopes())
                    .autoApprove(client.getAutoApprove())
                    .refreshTokenValiditySeconds(client.getRefreshTokenValiditySeconds())
                    .accessTokenValiditySeconds(client.getAccessTokenValiditySeconds())
                    .additionalInformation(client.getAdditionalInformation());

        }
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager).userDetailsService(userDetailsService).tokenStore(tokenStore);
        if(jwtAccessTokenConverter!=null && tokenEnhancer!=null){
            TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
            List<TokenEnhancer> tokenEnhancerList=new ArrayList<>();
            tokenEnhancerList.add(tokenEnhancer);
            tokenEnhancerList.add(jwtAccessTokenConverter);
            tokenEnhancerChain.setTokenEnhancers(tokenEnhancerList);
            endpoints.accessTokenConverter(jwtAccessTokenConverter).tokenEnhancer(tokenEnhancerChain);
        }
    }


}
