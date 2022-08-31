package com.ou.foodie.pojo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVo implements Serializable {
    private String id;
    private String username;
    private String nickname;
    private String face;

}
