package com.ou.foodie.security.configuration.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ou.foodie.enums.LoginType;
import com.ou.foodie.properties.ProjectProperties;
import com.ou.foodie.util.JsonResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Configuration
@AllArgsConstructor
@Slf4j
public class LoginFailHandler extends SimpleUrlAuthenticationFailureHandler {

    private ProjectProperties properties;
    private ObjectMapper objectMapper;


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (properties.getFormLogin().getLoginType().equals(LoginType.JSON)) {
            response.setContentType("application/json;charset=utf-8");
            log.error(exception.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            response.getWriter().write(objectMapper.writeValueAsString(JsonResult.Parmerror("登录失败",exception.getMessage())));

        }else {
            super.onAuthenticationFailure(request,response,exception);
        }
    }
}
