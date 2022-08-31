package com.ou.foodie.social.custom;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomOauth2Template extends OAuth2Template {

    public CustomOauth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
    }

    public String buildAuthenticateUrl(OAuth2Parameters parameters){
        List<String> scope=new ArrayList<>();
        scope.add("all");
        parameters.put("scope",scope);
        return super.buildAuthenticateUrl(parameters);

    };

    public AccessGrant exchangeForAccess(String authorizationCode, String redirectUri, MultiValueMap<String, String> additionalParameters) {
        List<String> scope=new ArrayList<>();
        scope.add("all");
        additionalParameters=new LinkedMultiValueMap<>();
        additionalParameters.put("scope",scope);
        return super.exchangeForAccess(authorizationCode, redirectUri, additionalParameters);
    }


    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }
}
