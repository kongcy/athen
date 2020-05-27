package com.athen.order.api.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/** no comment on table --> order */
@Data
public class Order implements Serializable {
    private Long id;

    /** 订单编号 --> order_no */
    private String orderNo;

    /** 客户编号 --> member_id */
    private Long memberId;

    /** 商户编码 --> supplier_id */
    private Long supplierId;

    /** 商户名称 --> supplier_name */
    private String supplierName;

    /** 订单状态 0未付款,1已付款,2已发货,3已签收,-1退货申请,-2退货中,-3已退货,-4取消交易 --> order_status */
    private Integer orderStatus;

    /** 用户售后状态 0 未发起售后 1 申请售后 -1 售后已取消 2 处理中 200 处理完毕 --> after_status */
    private Integer afterStatus;

    /** 商品总价 --> product_amount_total */
    private Long productAmountTotal;

    /** 实际付款金额 --> order_amount_total */
    private Long orderAmountTotal;

    /** 运费金额 --> logistics_fee */
    private Long logisticsFee;

    /** 收货地址 --> address */
    private String address;

    /** 支付渠道 0余额 1微信 2支付宝 --> pay_channel */
    private Integer payChannel;

    /** 第三方支付流水号 --> trade_no */
    private String tradeNo;

    /** 为 "payTime" 提供查询的起始值 */
    private Date payTimeStart;
    /** 为 "payTime" 提供查询的结束值 */
    private Date payTimeEnd;
    /** 付款时间 --> pay_time */
    private Date payTime;

    /** 为 "deliveryTime" 提供查询的起始值 */
    private Date deliveryTimeStart;
    /** 为 "deliveryTime" 提供查询的结束值 */
    private Date deliveryTimeEnd;
    /** 发货时间 --> delivery_time */
    private Date deliveryTime;

    /** 为 "finishTime" 提供查询的起始值 */
    private Date finishTimeStart;
    /** 为 "finishTime" 提供查询的结束值 */
    private Date finishTimeEnd;
    /** 订单完成时间 --> finish_time */
    private Date finishTime;

    /** 订单结算状态 0未结算 1已结算 --> order_settlement_status */
    private Integer orderSettlementStatus;

    /** 为 "orderSettlementTime" 提供查询的起始值 */
    private Date orderSettlementTimeStart;
    /** 为 "orderSettlementTime" 提供查询的结束值 */
    private Date orderSettlementTimeEnd;
    /** 订单结算时间 --> order_settlement_time */
    private Date orderSettlementTime;

    /** 是否是套餐 --> is_package */
    private Integer isPackage;

    /** 是否是积分产品 --> is_integral */
    private Integer isIntegral;

    /** 为 "createTime" 提供查询的起始值 */
    private Date createTimeStart;
    /** 为 "createTime" 提供查询的结束值 */
    private Date createTimeEnd;
    /** 下單時間 --> create_time */
    private Date createTime;

    /** 为 "updateTime" 提供查询的起始值 */
    private Date updateTimeStart;
    /** 为 "updateTime" 提供查询的结束值 */
    private Date updateTimeEnd;
    /** 修改订单时间 --> update_time */
    private Date updateTime;

    /** 为 "deleteTime" 提供查询的起始值 */
    private Date deleteTimeStart;
    /** 为 "deleteTime" 提供查询的结束值 */
    private Date deleteTimeEnd;
    /** delete_time */
    private Date deleteTime;

    private static final long serialVersionUID = 1L;
}