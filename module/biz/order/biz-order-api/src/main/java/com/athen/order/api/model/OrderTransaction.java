package com.athen.order.api.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/** no comment on table --> order_transaction */
@Data
public class OrderTransaction implements Serializable {
    private Long id;

    /** 交易单号(第三方生成) --> trade_no */
    private String tradeNo;

    /** 支付账号 --> account */
    private String account;

    /** 交易的用户ID --> member_id */
    private Long memberId;

    /** 交易金额 --> amount */
    private Long amount;

    /** 使用的积分 --> integral */
    private Integer integral;

    /** 支付状态 0 完成 1取消 --> pay_state */
    private Integer payState;

    /** 支付来源 wx app web wap --> source */
    private Integer source;

    /** 订单ID --> order_id */
    private Long orderId;

    /** 支付状态 -1：取消 0 未完成 1已完成 -2:异常 --> status */
    private Integer status;

    /** 为 "completionTime" 提供查询的起始值 */
    private Date completionTimeStart;
    /** 为 "completionTime" 提供查询的结束值 */
    private Date completionTimeEnd;
    /** 交易完成时间 --> completion_time */
    private Date completionTime;

    /** 支付消息 --> message */
    private String message;

    /** 为 "replyTime" 提供查询的起始值 */
    private Date replyTimeStart;
    /** 为 "replyTime" 提供查询的结束值 */
    private Date replyTimeEnd;
    /** 支付回调时间 --> reply_time */
    private Date replyTime;

    /** 备注 --> note */
    private String note;

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