package com.ou.foodie.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.naming.ldap.PagedResultsControl;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class UserInfoMoreBo {
    private String id;
    private String username;
    private String password;
    private String confirmPassword;
    @NotBlank(message = "用户昵称不能为空")
    @Length(max = 12,message = "用户昵称不能超过12位")
    private String realname;
    @Min(value = 0,message = "性别选择不正确")
    @Max(value = 2,message = "性别选择不正确")
    private Integer sex;
    private Date birthday;
    @Pattern(regexp = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$")
    private String mobile;
    @Length(max = 12,message = "用户昵称不能超过12位")
    private String nickname;
    private String email;
    private Date createdTime;
    private Date updatedTime;
    private String face;



}
