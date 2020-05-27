package com.athen.order.api.handler;

/**
 * Created by chenying on 2019-01-26.
 * 获取枚举处理类包名,订单模板所有的Handler都放在该包下
 */
public abstract class DefaultHandler {
    public static String PACK_NAME = DefaultHandler.class.getPackage().getName();
}
