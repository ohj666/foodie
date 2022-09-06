package com.ou.foodie.pojo.bo;

import lombok.Data;

@Data
public class CommentsBo {
    private String itemId;
    private Integer level=1;
    private Integer page=1;
    private Integer pageSize=10;

}
