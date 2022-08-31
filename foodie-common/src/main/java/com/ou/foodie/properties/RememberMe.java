package com.ou.foodie.properties;

import lombok.Data;

@Data
public class RememberMe {
        private String rememberMeCookieDomain="localhost";
        private String rememberMeParameter="remember-me";
        private String rememberMeCookieName="remember-me";
        private Boolean alwaysRemember = false;

}
