package com.ou.foodie.SessionStratgey;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ou.foodie.properties.ProjectProperties;
import com.ou.foodie.util.JsonResult;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.RedirectStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Data
public abstract class AbstractSessionStrategy {
    Boolean createSession;

    @Autowired
    private RedirectStrategy redirectStrategy;
    @Autowired
    private ProjectProperties properties;
    @Autowired
    private ObjectMapper objectMapper;
    protected void onSessionInvalid(HttpServletRequest request, HttpServletResponse response) throws IOException {
      log.info("session 失效 失效原因" +getErrorMsg());


        if(createSession){
            log.info("session 重写创建");
            request.getSession();
        }

        String action = request.getRequestURI();
        if(StringUtils.endsWith(action,".html")){
            redirectStrategy.sendRedirect(request,response,properties.getFormLogin().getLoginPage());

        }else {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(JsonResult.errorAuthorize(getErrorMsg(),null)));

        }


    }


    protected abstract String getErrorMsg();
}
