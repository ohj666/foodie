package com.ou.foodie.SessionStratgey;

import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class DefaultInvalidSessionStrategy extends AbstractSessionStrategy implements InvalidSessionStrategy {
    @Override
    protected String getErrorMsg() {
        return "DefaultInvalidSessionStrategy";
    }

    @Override
    public void onInvalidSessionDetected(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        onSessionInvalid(httpServletRequest,httpServletResponse);
    }
}
