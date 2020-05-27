package com.athen.order.repository;

import com.athen.order.api.model.OrderTransaction;
import com.athen.order.api.model.OrderTransactionExample;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.ResultHandler;

public interface OrderTransactionMapper {
    int countByExample(OrderTransactionExample example);

    int deleteByExample(OrderTransactionExample example);

    int deleteByPrimaryKey(Long id);

    int insertSelective(OrderTransaction record);

    List<OrderTransaction> selectByExample(OrderTransactionExample example, PageBounds page);

    void selectByExample(OrderTransactionExample example, ResultHandler handler);

    void selectByExample(OrderTransactionExample example, PageBounds page, ResultHandler handler);

    List<OrderTransaction> selectByExample(OrderTransactionExample example);

    OrderTransaction selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderTransaction record, @Param("example") OrderTransactionExample example);

    int updateByPrimaryKeySelective(OrderTransaction record);
}