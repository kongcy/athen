package com.athen.order.api.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/** no comment on table --> order_appraise */
@Data
public class OrderAppraise implements Serializable {
    private Long id;

    /** 订单编码 --> order_id */
    private Long orderId;

    /** 客户编号(评论人) --> member_id */
    private Long memberId;

    /** 客户账号(评论人账号) --> member_name */
    private String memberName;

    /** 级别 -1差评 0中评 1好评 --> level */
    private Integer level;

    /** 描述相符 1-5 --> desc_star */
    private Integer descStar;

    /** 物流服务 1-5 --> logistics_star */
    private Integer logisticsStar;

    /** 服务态度 1-5 --> attitude_star */
    private Integer attitudeStar;

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

    private static final long serialVersionUID = 1L;
}