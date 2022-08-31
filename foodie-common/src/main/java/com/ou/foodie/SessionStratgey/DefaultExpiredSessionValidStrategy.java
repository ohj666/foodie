package com.ou.foodie.SessionStratgey;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import java.io.IOException;
@Component
public class DefaultExpiredSessionValidStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {
    @Override
    protected String getErrorMsg() {
        return "DefaultInvalidSessionStrategy";
    }

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException, ServletException {
        onSessionInvalid(sessionInformationExpiredEvent.getRequest(),sessionInformationExpiredEvent.getResponse());
    }
}
