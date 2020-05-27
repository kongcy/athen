package com.athen.order.repository;

import com.athen.order.api.model.Order;
import com.athen.order.api.model.OrderExample;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.ResultHandler;

import java.util.List;

public interface OrderMapper {
    int countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Long id);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example, PageBounds page);

    void selectByExample(OrderExample example, ResultHandler handler);

    void selectByExample(OrderExample example, PageBounds page, ResultHandler handler);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);
}