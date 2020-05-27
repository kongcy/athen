package com.athen.system.web.controller.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.athen.core.Constant;
import com.athen.core.vo.DataTable;
import com.athen.order.api.model.Order;
import com.athen.order.api.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by foresee on 2019-02-01.
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController {
    /*
    @Reference(version = Constant.DUBBO_VERSION,group = "athenOrder")
    private OrderService orderService;

    @RequestMapping(value = "/list")
    public String list(){
        return "order/orderList";
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public DataTable<Order> list(DataTable<Order> dt, Order order) {
        return orderService.findPageUserByCondition(dt, order);
    }*/
}
