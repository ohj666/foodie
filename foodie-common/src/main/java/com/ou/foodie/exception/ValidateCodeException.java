package com.ou.foodie.exception;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg) {
        super(msg);
    }
}
