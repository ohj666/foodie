package com.ou.foodie.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import lombok.Builder;
import lombok.Data;

@Data
@Table(name = "`user_address`")
public class UserAddress implements Serializable {
    /**
     * 地址主键id
     */
    @Id
    @Column(name = "`id`")
    private String id;

    /**
     * 关联用户id
     */
    @Column(name = "`user_id`")
    private String userId;

    /**
     * 收件人姓名
     */
    @Column(name = "`receiver`")
    private String receiver;

    /**
     * 收件人手机号
     */
    @Column(name = "`mobile`")
    private String mobile;

    /**
     * 省份
     */
    @Column(name = "`province`")
    private String province;

    /**
     * 城市
     */
    @Column(name = "`city`")
    private String city;

    /**
     * 区县
     */
    @Column(name = "`district`")
    private String district;

    /**
     * 详细地址
     */
    @Column(name = "`detail`")
    private String detail;

    /**
     * 扩展字段
     */
    @Column(name = "`extand`")
    private String extand;

    /**
     * 是否默认地址
     */
    @Column(name = "`is_default`")
    private Integer isDefault;

    /**
     * 创建时间
     */
    @Column(name = "`created_time`")
    private Date createdTime;

    /**
     * 更新时间
     */
    @Column(name = "`updated_time`")
    private Date updatedTime;

    private static final long serialVersionUID = 1L;
}