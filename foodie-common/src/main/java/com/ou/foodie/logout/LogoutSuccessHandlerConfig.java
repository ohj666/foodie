package com.ou.foodie.logout;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ou.foodie.properties.ProjectProperties;
import com.ou.foodie.util.JsonResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
@AllArgsConstructor
@Component
public class LogoutSuccessHandlerConfig  implements LogoutSuccessHandler {
    private ProjectProperties properties;
    private ObjectMapper objectMapper;
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        log.info("出发退出成功逻辑");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(JsonResult.isOk(null)));

    }
}
