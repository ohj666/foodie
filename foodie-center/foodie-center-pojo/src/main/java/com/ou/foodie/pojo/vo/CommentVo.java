package com.ou.foodie.pojo.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CommentVo {
    private Date createdTime;
    private String userFace;
    private String nickname;
    private String content;
    private String specName;

}
