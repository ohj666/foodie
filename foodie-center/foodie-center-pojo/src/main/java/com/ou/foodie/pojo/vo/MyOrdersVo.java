package com.ou.foodie.pojo.vo;

import lombok.Data;

import javax.naming.ldap.PagedResultsControl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
public class MyOrdersVo implements Serializable {
    private String orderId;
    private Date createdTime;
    private Integer payMethod;
    private Integer realPayAmount;
    private Integer postAmount;
    private Integer isComment;
    private Integer orderStatus;
    private List<MySubOrderItemVo> subOrderItemList=new ArrayList<>();




}
