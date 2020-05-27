package com.athen.order.repository;

import com.athen.order.api.model.OrderReturnsApply;
import com.athen.order.api.model.OrderReturnsApplyExample;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.ResultHandler;

public interface OrderReturnsApplyMapper {
    int countByExample(OrderReturnsApplyExample example);

    int deleteByExample(OrderReturnsApplyExample example);

    int deleteByPrimaryKey(Long id);

    int insertSelective(OrderReturnsApply record);

    List<OrderReturnsApply> selectByExampleWithBLOBs(OrderReturnsApplyExample example, PageBounds page);

    void selectByExampleWithBLOBs(OrderReturnsApplyExample example, ResultHandler handler);

    void selectByExampleWithBLOBs(OrderReturnsApplyExample example, PageBounds page, ResultHandler handler);

    List<OrderReturnsApply> selectByExampleWithBLOBs(OrderReturnsApplyExample example);

    List<OrderReturnsApply> selectByExample(OrderReturnsApplyExample example, PageBounds page);

    void selectByExample(OrderReturnsApplyExample example, ResultHandler handler);

    void selectByExample(OrderReturnsApplyExample example, PageBounds page, ResultHandler handler);

    List<OrderReturnsApply> selectByExample(OrderReturnsApplyExample example);

    OrderReturnsApply selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderReturnsApply record, @Param("example") OrderReturnsApplyExample example);

    int updateByPrimaryKeySelective(OrderReturnsApply record);
}