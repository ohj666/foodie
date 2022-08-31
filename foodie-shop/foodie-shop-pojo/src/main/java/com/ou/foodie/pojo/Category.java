package com.ou.foodie.pojo;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "`category`")
public class Category implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "`id`")
    private Integer id;

    /**
     * 分类名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 分类类型
     */
    @Column(name = "`type`")
    private Integer type;

    /**
     * 父id
     */
    @Column(name = "`father_id`")
    private Integer fatherId;

    /**
     * 图标
     */
    @Column(name = "`logo`")
    private String logo;

    /**
     * 口号
     */
    @Column(name = "`slogan`")
    private String slogan;

    /**
     * 分类图
     */
    @Column(name = "`cat_image`")
    private String catImage;

    /**
     * 背景颜色
     */
    @Column(name = "`bg_color`")
    private String bgColor;

    private static final long serialVersionUID = 1L;
}