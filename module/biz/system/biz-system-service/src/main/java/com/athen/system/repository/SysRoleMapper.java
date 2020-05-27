package com.athen.system.repository;

import com.athen.system.api.model.SysRole;
import com.athen.system.api.model.SysRoleExample;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper {
    int countByExample(SysRoleExample example);

    int deleteByExample(SysRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insertSelective(SysRole record);

    List<SysRole> selectByExample(SysRoleExample example, PageBounds page);

    List<SysRole> selectByExample(SysRoleExample example);

    SysRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    int updateByPrimaryKeySelective(SysRole record);

    List<Long> findUserByRole(Long roleId);
}