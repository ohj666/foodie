package com.ou.foodie.social.custom;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;
@Data
public class CustomUserInfo {
    private String openId;
    private String nickName;
    private String figureurl;

}
