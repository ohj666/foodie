package com.ou.foodie.security.configuration.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ou.foodie.properties.ProjectProperties;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@AllArgsConstructor
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private ProjectProperties properties;
    private ObjectMapper objectMapper;
    private AuthorizationServerTokenServices tokenServices;


    private ClientDetailsService clientDetailsService;

    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        if(header==null||!header.startsWith("Basic ")){
            authenticationFailureHandler.onAuthenticationFailure(request,response,new UsernameNotFoundException("请求未包含请求头"));
            return;
        }
        String[] tokens = extractAndDecodeHeader(header, request);
        assert tokens.length==2;

        String clientId = tokens[0];
        String clientSecret = tokens[1];

        ClientDetails authenticatedClient = clientDetailsService.loadClientByClientId(clientId);
        TokenRequest tokenRequest = new TokenRequest(null,clientId,null,"custom");

        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(authenticatedClient);
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request,authentication);
        OAuth2AccessToken accessToken = tokenServices.createAccessToken(oAuth2Authentication);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(accessToken));

    }

    private String[] extractAndDecodeHeader(String header, HttpServletRequest request) throws IOException {
        byte[] base64Token = header.substring(6).getBytes("UTF-8");

        byte[] decoded;
        try {
            decoded = Base64.decode(base64Token);
        } catch (IllegalArgumentException var7) {
            throw new BadCredentialsException("Failed to decode basic authentication token");
        }

        String token = new String(decoded, "UTF-8");
        int delim = token.indexOf(":");
        if (delim == -1) {
            throw new BadCredentialsException("Invalid basic authentication token");
        } else {
            return new String[]{token.substring(0, delim), token.substring(delim + 1)};
        }
    }


}
