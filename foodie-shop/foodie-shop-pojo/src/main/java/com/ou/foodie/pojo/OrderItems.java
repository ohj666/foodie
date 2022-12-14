package com.ou.foodie.pojo;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "`order_items`")
public class OrderItems implements Serializable {
    /**
     * 主键id
     */
    @Id
    @Column(name = "`id`")
    private String id;

    /**
     * 归属订单id
     */
    @Column(name = "`order_id`")
    private String orderId;

    /**
     * 商品id
     */
    @Column(name = "`item_id`")
    private String itemId;

    /**
     * 商品图片
     */
    @Column(name = "`item_img`")
    private String itemImg;

    /**
     * 商品名称
     */
    @Column(name = "`item_name`")
    private String itemName;

    /**
     * 规格id
     */
    @Column(name = "`item_spec_id`")
    private String itemSpecId;

    /**
     * 规格名称
     */
    @Column(name = "`item_spec_name`")
    private String itemSpecName;

    /**
     * 成交价格
     */
    @Column(name = "`price`")
    private Integer price;

    /**
     * 购买数量
     */
    @Column(name = "`buy_counts`")
    private Integer buyCounts;

    private static final long serialVersionUID = 1L;
}