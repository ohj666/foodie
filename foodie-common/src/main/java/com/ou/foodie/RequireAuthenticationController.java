package com.ou.foodie;

import com.ou.foodie.properties.ProjectConstant;
import com.ou.foodie.properties.ProjectProperties;
import com.ou.foodie.util.JsonResult;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(ProjectConstant.REQUEST_AUTHENTICATION_URL)
@AllArgsConstructor
public class RequireAuthenticationController {
    private HttpServletResponse response;
    private HttpServletRequest request;

    private ProjectProperties properties;

    private RedirectStrategy redirectStrategy;

    @RequestMapping(produces = "text/html")
    public void errorHtml() throws IOException {
        redirectStrategy.sendRedirect(request,response,properties.getFormLogin().getLoginPage());
    }

    @RequestMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public JsonResult error(HttpServletRequest request){
        return JsonResult.errorAuthorize("当前用户没有权限访问资源，请重新登录",null);
    }


}
