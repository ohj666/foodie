package com.ou.foodie.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "`carousel`")
public class Carousel implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "`id`")
    private String id;

    /**
     * 图片 图片地址
     */
    @Column(name = "`image_url`")
    private String imageUrl;

    /**
     * 背景色
     */
    @Column(name = "`background_color`")
    private String backgroundColor;

    /**
     * 商品id 商品id
     */
    @Column(name = "`item_id`")
    private String itemId;

    /**
     * 商品分类id 商品分类id
     */
    @Column(name = "`cat_id`")
    private String catId;

    /**
     * 轮播图类型 轮播图类型，用于判断，可以根据商品id或者分类进行页面跳转，1：商品 2：分类
     */
    @Column(name = "`type`")
    private Integer type;

    /**
     * 轮播图展示顺序
     */
    @Column(name = "`sort`")
    private Integer sort;

    /**
     * 是否展示
     */
    @Column(name = "`is_show`")
    private Integer isShow;

    /**
     * 创建时间 创建时间
     */
    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 更新时间 更新
     */
    @Column(name = "`update_time`")
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}