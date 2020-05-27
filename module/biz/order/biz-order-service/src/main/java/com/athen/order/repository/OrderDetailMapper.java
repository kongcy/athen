package com.athen.order.repository;

import com.athen.order.api.model.OrderDetail;
import com.athen.order.api.model.OrderDetailExample;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.ResultHandler;

public interface OrderDetailMapper {
    int countByExample(OrderDetailExample example);

    int deleteByExample(OrderDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insertSelective(OrderDetail record);

    List<OrderDetail> selectByExample(OrderDetailExample example, PageBounds page);

    void selectByExample(OrderDetailExample example, ResultHandler handler);

    void selectByExample(OrderDetailExample example, PageBounds page, ResultHandler handler);

    List<OrderDetail> selectByExample(OrderDetailExample example);

    OrderDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderDetail record, @Param("example") OrderDetailExample example);

    int updateByPrimaryKeySelective(OrderDetail record);
}