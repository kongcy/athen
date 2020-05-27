package com.athen.order.api.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/** 订单跟踪表 --> order_track */
@Data
public class OrderTrack implements Serializable {
    private Long id;

    /** 订单ID --> ORDER_ID */
    private Long orderId;

    /** 更新人 --> UPDATE_USER */
    private Long updateUser;

    /** 更新人的身份(用户 0 ,商家 1) --> USER_TYPE */
    private Integer userType;

    /** 为 "updateTime" 提供查询的起始值 */
    private Date updateTimeStart;
    /** 为 "updateTime" 提供查询的结束值 */
    private Date updateTimeEnd;
    /** 更新时间 --> UPDATE_TIME */
    private Date updateTime;

    /** 订单状态 --> ORDER_STATE */
    private Integer orderState;

    /** 操作(付款 0,确认付款 1,退款 2 删除订单 3..) --> OPERATE_TYPE */
    private Integer operateType;

    /** 备注 --> MARK */
    private String mark;

    private static final long serialVersionUID = 1L;
}