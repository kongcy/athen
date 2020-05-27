package com.athen.order.repository;

import com.athen.order.api.model.OrderAppraise;
import com.athen.order.api.model.OrderAppraiseExample;
import com.athen.order.api.model.OrderAppraiseWithBLOBs;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.ResultHandler;

public interface OrderAppraiseMapper {
    int countByExample(OrderAppraiseExample example);

    int deleteByExample(OrderAppraiseExample example);

    int deleteByPrimaryKey(Long id);

    int insertSelective(OrderAppraiseWithBLOBs record);

    List<OrderAppraiseWithBLOBs> selectByExampleWithBLOBs(OrderAppraiseExample example, PageBounds page);

    void selectByExampleWithBLOBs(OrderAppraiseExample example, ResultHandler handler);

    void selectByExampleWithBLOBs(OrderAppraiseExample example, PageBounds page, ResultHandler handler);

    List<OrderAppraiseWithBLOBs> selectByExampleWithBLOBs(OrderAppraiseExample example);

    List<OrderAppraise> selectByExample(OrderAppraiseExample example, PageBounds page);

    void selectByExample(OrderAppraiseExample example, ResultHandler handler);

    void selectByExample(OrderAppraiseExample example, PageBounds page, ResultHandler handler);

    List<OrderAppraise> selectByExample(OrderAppraiseExample example);

    OrderAppraiseWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderAppraiseWithBLOBs record, @Param("example") OrderAppraiseExample example);

    int updateByPrimaryKeySelective(OrderAppraiseWithBLOBs record);
}