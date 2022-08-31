package com.ou.foodie.vaildate;

import com.ou.foodie.exception.ValidateCodeException;
import com.ou.foodie.vaildate.processor.ValidateCodeProcessor;
import lombok.Data;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Data
public class ValidateCodeFilter extends OncePerRequestFilter {

    private AuthenticationFailureHandler authenticationFailureHandler;
    List<ValidateCodeProcessor> validateCodeProcessors;



    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        ServletWebRequest servletWebRequest = new ServletWebRequest(httpServletRequest, httpServletResponse);
        for (ValidateCodeProcessor validateCodeProcessor : validateCodeProcessors) {
            if(validateCodeProcessor.isNeedValida(servletWebRequest)){
                try{
                    validateCodeProcessor.validate(servletWebRequest);
                    filterChain.doFilter(httpServletRequest,httpServletResponse);
                    return;
                }catch (ValidateCodeException e){
                    authenticationFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
                    return;
                }

            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
