package com.ou.foodie.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "`items_spec`")
public class ItemsSpec implements Serializable {
    /**
     * 商品规格id
     */
    @Id
    @Column(name = "`id`")
    private String id;

    /**
     * 商品外键id
     */
    @Column(name = "`item_id`")
    private String itemId;

    /**
     * 规格名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 库存
     */
    @Column(name = "`stock`")
    private Integer stock;

    /**
     * 折扣力度
     */
    @Column(name = "`discounts`")
    private BigDecimal discounts;

    /**
     * 优惠价
     */
    @Column(name = "`price_discount`")
    private Integer priceDiscount;

    /**
     * 原价
     */
    @Column(name = "`price_normal`")
    private Integer priceNormal;

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