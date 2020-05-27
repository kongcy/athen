package com.athen.order.api.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/** no comment on table --> order_returns */
@Data
public class OrderReturns implements Serializable {
    private Long id;

    /** 退货编号 供客户查询 --> returns_no */
    private String returnsNo;

    /** 订单编号 --> order_id */
    private Long orderId;

    /** 物流单号 --> express_no */
    private String expressNo;

    /** 收货人姓名 --> consignee_realname */
    private String consigneeRealname;

    /** 联系电话 --> consignee_telphone */
    private String consigneeTelphone;

    /** 备用联系电话 --> consignee_telphone2 */
    private String consigneeTelphone2;

    /** 收货地址 --> consignee_address */
    private String consigneeAddress;

    /** 邮政编码 --> consignee_zip */
    private String consigneeZip;

    /** 物流方式 --> logistics_type */
    private String logisticsType;

    /** 物流发货运费 --> logistics_fee */
    private BigDecimal logisticsFee;

    /** 物流状态 --> order_logistics_status */
    private Integer orderLogisticsStatus;

    /** 物流结算状态 --> logistics_settlement_status */
    private Integer logisticsSettlementStatus;

    /** 物流最后状态描述 --> logistics_result_last */
    private String logisticsResultLast;

    /** 物流描述 --> logistics_result */
    private String logisticsResult;

    /** 为 "logisticsCreateTime" 提供查询的起始值 */
    private Date logisticsCreateTimeStart;
    /** 为 "logisticsCreateTime" 提供查询的结束值 */
    private Date logisticsCreateTimeEnd;
    /** 发货时间 --> logistics_create_time */
    private Date logisticsCreateTime;

    /** 为 "logisticsUpdateTime" 提供查询的起始值 */
    private Date logisticsUpdateTimeStart;
    /** 为 "logisticsUpdateTime" 提供查询的结束值 */
    private Date logisticsUpdateTimeEnd;
    /** 物流更新时间 --> logistics_update_time */
    private Date logisticsUpdateTime;

    /** 为 "logisticsSettlementTime" 提供查询的起始值 */
    private Date logisticsSettlementTimeStart;
    /** 为 "logisticsSettlementTime" 提供查询的结束值 */
    private Date logisticsSettlementTimeEnd;
    /** 物流结算时间 --> logistics_settlement_time */
    private Date logisticsSettlementTime;

    /** 0全部退单 1部分退单 --> returns_type */
    private Integer returnsType;

    /** PUPAWAY:退货入库;REDELIVERY:重新发货;RECLAIM-REDELIVERY:不要求归还并重新发货; REFUND:退款; COMPENSATION:不退货并赔偿 --> handling_way */
    private Integer handlingWay;

    /** 退款金额 --> returns_amount */
    private Long returnsAmount;

    /** 为 "returnSubmitTime" 提供查询的起始值 */
    private Date returnSubmitTimeStart;
    /** 为 "returnSubmitTime" 提供查询的结束值 */
    private Date returnSubmitTimeEnd;
    /** 退货申请时间 --> return_submit_time */
    private Date returnSubmitTime;

    /** 为 "handlingTime" 提供查询的起始值 */
    private Date handlingTimeStart;
    /** 为 "handlingTime" 提供查询的结束值 */
    private Date handlingTimeEnd;
    /** 退货处理时间 --> handling_time */
    private Date handlingTime;

    /** 退货原因 --> remark */
    private String remark;

    private static final long serialVersionUID = 1L;
}