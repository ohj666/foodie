package com.ou.foodie.properties;

import com.ou.foodie.enums.LoginType;
import lombok.Data;


@Data
public class FormLoginProperties {
    private String loginPage;
    private String LoginProcessingUrl;
    private String usernameParam;
    private String passwordParam;
    private LoginType loginType;


}
