package com.athen.order.api.service;

import com.athen.core.vo.DataTable;
import com.athen.order.api.model.Order;

/**
 * Created by foresee on 2019-02-01.
 * 订单服务类
 */
public interface OrderService {

    /**
     * 根据条件查询订单列表*
     */
    public DataTable<Order> findPageUserByCondition(DataTable<Order> dt, Order user);



}
