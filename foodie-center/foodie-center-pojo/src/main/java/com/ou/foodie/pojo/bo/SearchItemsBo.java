package com.ou.foodie.pojo.bo;

import lombok.Data;

import java.io.Serializable;
@Data
public class SearchItemsBo implements Serializable {
    private String itemName;
    private String sort;
}
