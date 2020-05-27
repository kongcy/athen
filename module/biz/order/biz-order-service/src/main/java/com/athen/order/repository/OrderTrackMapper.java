package com.athen.order.repository;

import com.athen.order.api.model.OrderTrack;
import com.athen.order.api.model.OrderTrackExample;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.ResultHandler;

public interface OrderTrackMapper {
    int countByExample(OrderTrackExample example);

    int deleteByExample(OrderTrackExample example);

    int deleteByPrimaryKey(Long id);

    int insertSelective(OrderTrack record);

    List<OrderTrack> selectByExample(OrderTrackExample example, PageBounds page);

    void selectByExample(OrderTrackExample example, ResultHandler handler);

    void selectByExample(OrderTrackExample example, PageBounds page, ResultHandler handler);

    List<OrderTrack> selectByExample(OrderTrackExample example);

    OrderTrack selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderTrack record, @Param("example") OrderTrackExample example);

    int updateByPrimaryKeySelective(OrderTrack record);
}