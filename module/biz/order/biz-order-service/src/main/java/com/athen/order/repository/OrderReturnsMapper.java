package com.athen.order.repository;

import com.athen.order.api.model.OrderReturns;
import com.athen.order.api.model.OrderReturnsExample;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.ResultHandler;

public interface OrderReturnsMapper {
    int countByExample(OrderReturnsExample example);

    int deleteByExample(OrderReturnsExample example);

    int deleteByPrimaryKey(Long id);

    int insertSelective(OrderReturns record);

    List<OrderReturns> selectByExampleWithBLOBs(OrderReturnsExample example, PageBounds page);

    void selectByExampleWithBLOBs(OrderReturnsExample example, ResultHandler handler);

    void selectByExampleWithBLOBs(OrderReturnsExample example, PageBounds page, ResultHandler handler);

    List<OrderReturns> selectByExampleWithBLOBs(OrderReturnsExample example);

    List<OrderReturns> selectByExample(OrderReturnsExample example, PageBounds page);

    void selectByExample(OrderReturnsExample example, ResultHandler handler);

    void selectByExample(OrderReturnsExample example, PageBounds page, ResultHandler handler);

    List<OrderReturns> selectByExample(OrderReturnsExample example);

    OrderReturns selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderReturns record, @Param("example") OrderReturnsExample example);

    int updateByPrimaryKeySelective(OrderReturns record);
}