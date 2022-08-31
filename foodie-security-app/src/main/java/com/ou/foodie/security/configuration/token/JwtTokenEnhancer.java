package com.ou.foodie.security.configuration.token;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import sun.tools.jconsole.AboutDialog;

import java.util.HashMap;
import java.util.Map;

public class JwtTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Map<String,Object> additionalInformation=new HashMap<>();
        additionalInformation.put("company","seehope");
        additionalInformation.put("name","lin");


        DefaultOAuth2AccessToken currentAccessToken= (DefaultOAuth2AccessToken) oAuth2AccessToken;
        currentAccessToken.setAdditionalInformation(additionalInformation);
        return currentAccessToken;
    }
}
