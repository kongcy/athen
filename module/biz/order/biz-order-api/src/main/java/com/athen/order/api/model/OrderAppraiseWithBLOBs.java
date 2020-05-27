package com.athen.order.api.model;

import java.io.Serializable;

/** no comment on table --> order_appraise */
public class OrderAppraiseWithBLOBs extends OrderAppraise implements Serializable {
    /** 图片地址，多张 --> url */
    private String url;

    /** 评论内容 --> info */
    private String info;

    private static final long serialVersionUID = 1L;
}