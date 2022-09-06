package com.ou.foodie.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
@Builder
public class UserBo implements Serializable {

    private String username;
    private String password;
    private String confirmPassword;
}
