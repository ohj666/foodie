package com.ou.foodie.pojo.vo;

import lombok.Data;

import java.util.Date;

@Data
public class HistoryCommentVo {
    private String itemImg;
    private String specName;
    private Date createdTime;
    private String itemName;
    private String content;
}
