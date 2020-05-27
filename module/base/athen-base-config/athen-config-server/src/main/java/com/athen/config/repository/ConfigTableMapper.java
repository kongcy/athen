package com.athen.config.repository;

import com.athen.config.model.ConfigTable;
import com.athen.config.model.ConfigTableExample;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.ResultHandler;

import java.util.List;
import java.util.Map;

public interface ConfigTableMapper {
    int countByExample(ConfigTableExample example);

    int deleteByExample(ConfigTableExample example);

    int deleteByPrimaryKey(Long id);

    int insertSelective(ConfigTable record);

    List<ConfigTable> selectByExample(ConfigTableExample example, PageBounds page);

    void selectByExample(ConfigTableExample example, ResultHandler handler);

    void selectByExample(ConfigTableExample example, PageBounds page, ResultHandler handler);

    List<ConfigTable> selectByExample(ConfigTableExample example);

    ConfigTable selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ConfigTable record, @Param("example") ConfigTableExample example);

    int updateByPrimaryKeySelective(ConfigTable record);

    List<Map<String,String>> findTables();

    List<ConfigTable> selectByExampleExt(ConfigTableExample example);
}