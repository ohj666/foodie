package com.ou.foodie.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegisterBo {
    @Length(max = 30,min = 2)
    @NotNull
    private String username;
    @Length(max = 30,min = 2)
    @NotNull
    private String password;
    @Length(max = 30,min = 2)
    @NotNull
    private String rePassword;
}
