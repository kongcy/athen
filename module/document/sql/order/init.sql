
-- ----------------------------
-- 订单交易表
-- ----------------------------
DROP TABLE IF EXISTS `order_transaction`;
CREATE TABLE `order_transaction` (
  `id` bigint(20)  NOT NULL AUTO_INCREMENT,
  `trade_no` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '交易单号(第三方生成)',
  `account` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '支付账号',
  `member_id` bigint(20) NOT NULL COMMENT '交易的用户ID',
  `amount` bigint(20) NOT NULL COMMENT '交易金额',
  `integral` int(8) NOT NULL DEFAULT '0' COMMENT '使用的积分',
  `pay_state` int(4) NOT NULL COMMENT '支付状态 0 完成 1取消',
  `source` int(4) NOT NULL COMMENT '支付来源 wx app web wap',
  `order_id` bigint(20) NOT NULL COMMENT '订单ID',
  `status` int(4) NOT NULL DEFAULT '0' COMMENT '支付状态 -1：取消 0 未完成 1已完成 -2:异常',
  `completion_time` datetime NOT NULL COMMENT '交易完成时间',
  `message` varchar(64) DEFAULT NULL COMMENT '支付消息',
  `reply_time` datetime DEFAULT NULL COMMENT '支付回调时间',
  `note` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;




-- ----------------------------
-- 订单表
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '订单编号',
  `member_id` bigint(20) NOT NULL COMMENT '客户编号',
  `supplier_id` bigint(20) NOT NULL COMMENT '商户编码',
  `supplier_name` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商户名称',
  `order_status` int(4) NOT NULL DEFAULT '0' COMMENT '订单状态 0未付款,1已付款,2已发货,3已签收,-1退货申请,-2退货中,-3已退货,-4取消交易',
  `after_status` int(4) NOT NULL DEFAULT '0' COMMENT '用户售后状态 0 未发起售后 1 申请售后 -1 售后已取消 2 处理中 200 处理完毕',
  `product_amount_total` bigint(20) NOT NULL COMMENT '商品总价',
  `order_amount_total` bigint(20) NOT NULL DEFAULT '0' COMMENT '实际付款金额',
  `logistics_fee` bigint(20) NOT NULL COMMENT '运费金额',
  `address` varchar(128) NOT NULL COMMENT '收货地址',
  `pay_channel` int(4) NOT NULL DEFAULT '0' COMMENT '支付渠道 0余额 1微信 2支付宝',
  `trade_no` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '第三方支付流水号',
  `pay_time` datetime COMMENT '付款时间',
  `delivery_time` datetime COMMENT '发货时间',
  `finish_time` datetime COMMENT '订单完成时间',
  `order_settlement_status` int(4) NOT NULL DEFAULT '0' COMMENT '订单结算状态 0未结算 1已结算',
  `order_settlement_time` datetime COMMENT '订单结算时间',
  `is_package` int(4) NOT NULL DEFAULT '0' COMMENT '是否是套餐',
  `is_integral` int(4) NOT NULL DEFAULT '0' COMMENT '是否是积分产品',
  `create_time` datetime NOT NULL COMMENT '下單時間',
  `update_time` datetime DEFAULT NULL COMMENT '修改订单时间',
  `delete_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_order_no_unique` (`order_no`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



/*==============================================================*/
/* Table: T_ORDER_DETAIL 订单详细表                           */
/*==============================================================*/
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE order_detail (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id`bigint(20) NOT NULL COMMENT '商品ID',
  `product_name` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品名称',
  `product_count` int(11) NOT NULL DEFAULT '0' COMMENT '商品数量',
  `product_amount` bigint(20) NOT NULL COMMENT '商品单价',
  `order_id` bigint(20) NOT NULL COMMENT '订单ID',
  `member_id` bigint(20) NOT NULL COMMENT '客户编号',
  `supplier_id` bigint(20) NOT NULL COMMENT '商户编码',
  `supplier_name` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商户名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
-- ----------------------------
-- Table structure for T_ORDER_TRACK 订单跟踪表
-- ----------------------------
DROP TABLE IF EXISTS `order_track`;
CREATE TABLE `order_track` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ORDER_ID` bigint(20) NOT NULL COMMENT '订单ID',
  `UPDATE_USER` bigint(20) NOT NULL COMMENT '更新人',
  `USER_TYPE` int(4) NOT NULL COMMENT '更新人的身份(用户 0 ,商家 1)',
  `UPDATE_TIME` datetime NOT NULL COMMENT '更新时间',
  `ORDER_STATE` int(4) NOT NULL COMMENT '订单状态',
  `OPERATE_TYPE` int(4) NOT NULL COMMENT '操作(付款 0,确认付款 1,退款 2 删除订单 3..)',
  `MARK` varchar(128) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='订单跟踪表';
-- ----------------------------
-- 售后申请表
-- ----------------------------
DROP TABLE IF EXISTS `order_returns_apply`;
CREATE TABLE `order_returns_apply`(
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '订单单号',
  `order_detail_id` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '子订单编码',
  `return_no` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '售后单号',
  `member_id` bigint(20) NOT NULL COMMENT '用户编码',
  `state` int(4) NOT NULL COMMENT '类型 0 仅退款 1退货退款',
  `product_status` int(4) NOT NULL DEFAULT '0' COMMENT '货物状态 0:已收到货 1:未收到货',
  `why` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '退换货原因',
  `status` int(4) NOT NULL DEFAULT '0' COMMENT '审核状态 -1 拒绝 0 未审核 1审核通过',
  `audit_time` datetime COMMENT '审核时间',
  `audit_why` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT'审核原因',
  `note` text COLLATE utf8mb4_unicode_ci COMMENT '备注',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  售后物流表
-- ----------------------------
DROP TABLE IF EXISTS `order_returns`;
CREATE TABLE `order_returns`(
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `returns_no` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '退货编号 供客户查询',
  `order_id` bigint(20) NOT NULL COMMENT '订单编号',
  `express_no` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '物流单号',
  `consignee_realname` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '收货人姓名',
  `consignee_telphone` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '联系电话',
  `consignee_telphone2` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '备用联系电话',
  `consignee_address` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '收货地址',
  `consignee_zip` varchar(8) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '邮政编码',
  `logistics_type` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '物流方式',
  `logistics_fee` decimal(12,2) NOT NULL COMMENT '物流发货运费',
  `order_logistics_status` int(11) DEFAULT NULL COMMENT '物流状态',
  `logistics_settlement_status` int(11) DEFAULT NULL COMMENT '物流结算状态',
  `logistics_result_last` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '物流最后状态描述',
  `logistics_result` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '物流描述',
  `logistics_create_time` datetime DEFAULT NULL COMMENT '发货时间',
  `logistics_update_time` datetime DEFAULT NULL COMMENT '物流更新时间',
  `logistics_settlement_time` datetime DEFAULT NULL COMMENT '物流结算时间',
  `returns_type` int(4) NOT NULL DEFAULT '0' COMMENT '0全部退单 1部分退单',
  `handling_way` int NOT NULL COMMENT 'PUPAWAY:退货入库;REDELIVERY:重新发货;RECLAIM-REDELIVERY:不要求归还并重新发货; REFUND:退款; COMPENSATION:不退货并赔偿',
  `returns_amount` bigint(20) NOT NULL COMMENT '退款金额',
  `return_submit_time` datetime NOT NULL COMMENT '退货申请时间',
  `handling_time` datetime NOT NULL COMMENT '退货处理时间',
  `remark` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '退货原因',
  PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- 评价数据表
-- ----------------------------
DROP TABLE IF EXISTS `order_appraise`;
CREATE TABLE `order_appraise`(
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL COMMENT '订单编码',
  `member_id` bigint(20) NOT NULL COMMENT '客户编号(评论人)',
  `member_name` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '客户账号(评论人账号)',
  `url` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图片地址，多张',
  `info` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论内容',
  `level` int(4) NOT NULL COMMENT '级别 -1差评 0中评 1好评',
  `desc_star` int(4) NOT NULL COMMENT '描述相符 1-5',
  `logistics_star` int(4) NOT NULL COMMENT '物流服务 1-5',
  `attitude_star` int(4) NOT NULL COMMENT '服务态度 1-5',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_appraise_order_id_index` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
