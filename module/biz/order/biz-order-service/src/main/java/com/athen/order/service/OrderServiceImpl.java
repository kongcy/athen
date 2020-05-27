package com.athen.order.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.athen.core.Constant;
import com.athen.core.vo.DataTable;
import com.athen.order.api.model.Order;
import com.athen.order.api.model.OrderExample;
import com.athen.order.api.service.OrderService;
import com.athen.order.repository.OrderMapper;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by foresee on 2019-02-01.
 */
@Service(version = Constant.DUBBO_VERSION, group = "athenOrder", interfaceClass = OrderService.class)
@Component
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    /**
     * 根据条件查询订单列表*
     *
     * @param dt
     * @param user
     */
    @Override
    public DataTable<Order> findPageUserByCondition(DataTable<Order> dt, Order user) {
        PageBounds page = new PageBounds(dt.pageNo(), dt.getiDisplayLength());
        OrderExample example = null;
        List<Order> orders = orderMapper.selectByExample(example, page);
        dt.setAaData(orders);
        dt.setiTotalDisplayRecords(((PageList) orders).getPaginator().getTotalCount());
        return dt;
    }
}
