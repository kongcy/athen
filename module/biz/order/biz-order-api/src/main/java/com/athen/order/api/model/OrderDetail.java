package com.athen.order.api.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/** no comment on table --> order_detail */
@Data
public class OrderDetail implements Serializable {
    private Long id;

    /** 商品ID --> product_id */
    private Long productId;

    /** 商品名称 --> product_name */
    private String productName;

    /** 商品数量 --> product_count */
    private Integer productCount;

    /** 商品单价 --> product_amount */
    private Long productAmount;

    /** 订单ID --> order_id */
    private Long orderId;

    /** 客户编号 --> member_id */
    private Long memberId;

    /** 商户编码 --> supplier_id */
    private Long supplierId;

    /** 商户名称 --> supplier_name */
    private String supplierName;

    /** 为 "createTime" 提供查询的起始值 */
    private Date createTimeStart;
    /** 为 "createTime" 提供查询的结束值 */
    private Date createTimeEnd;
    /** 创建时间 --> create_time */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}