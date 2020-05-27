package com.athen.system.repository;

import com.athen.system.api.model.SysResource;
import com.athen.system.api.model.SysResourceExample;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysResourceMapper {
    int countByExample(SysResourceExample example);

    int deleteByExample(SysResourceExample example);

    int deleteByPrimaryKey(Long id);

    int insertSelective(SysResource record);

    List<SysResource> selectByExample(SysResourceExample example, PageBounds page);

    List<SysResource> selectByExample(SysResourceExample example);

    SysResource selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysResource record, @Param("example") SysResourceExample example);

    int updateByPrimaryKeySelective(SysResource record);

    List<SysResource> findResByInUserId(Long userId);
}