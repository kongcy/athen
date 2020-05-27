package com.athen.order.api.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/** no comment on table --> order_returns_apply */
@Data
public class OrderReturnsApply implements Serializable {
    private Long id;

    /** 订单单号 --> order_no */
    private String orderNo;

    /** 子订单编码 --> order_detail_id */
    private String orderDetailId;

    /** 售后单号 --> return_no */
    private String returnNo;

    /** 用户编码 --> member_id */
    private Long memberId;

    /** 类型 0 仅退款 1退货退款 --> state */
    private Integer state;

    /** 货物状态 0:已收到货 1:未收到货 --> product_status */
    private Integer productStatus;

    /** 退换货原因 --> why */
    private String why;

    /** 审核状态 -1 拒绝 0 未审核 1审核通过 --> status */
    private Integer status;

    /** 为 "auditTime" 提供查询的起始值 */
    private Date auditTimeStart;
    /** 为 "auditTime" 提供查询的结束值 */
    private Date auditTimeEnd;
    /** 审核时间 --> audit_time */
    private Date auditTime;

    /** 审核原因 --> audit_why */
    private String auditWhy;

    /** 为 "createTime" 提供查询的起始值 */
    private Date createTimeStart;
    /** 为 "createTime" 提供查询的结束值 */
    private Date createTimeEnd;
    /** create_time */
    private Date createTime;

    /** 为 "updateTime" 提供查询的起始值 */
    private Date updateTimeStart;
    /** 为 "updateTime" 提供查询的结束值 */
    private Date updateTimeEnd;
    /** update_time */
    private Date updateTime;

    /** 备注 --> note */
    private String note;

    private static final long serialVersionUID = 1L;
}