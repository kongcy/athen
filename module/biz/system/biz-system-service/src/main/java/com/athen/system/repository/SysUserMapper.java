package com.athen.system.repository;

import com.athen.system.api.model.SysUser;
import com.athen.system.api.model.SysUserExample;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
public interface SysUserMapper {
    int countByExample(SysUserExample example);

    int deleteByExample(SysUserExample example);

    int deleteByPrimaryKey(Long id);

    int insertSelective(SysUser record);

    List<SysUser> selectByExample(SysUserExample example, PageBounds page);

    List<SysUser> selectByExample(SysUserExample example);

    SysUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByPrimaryKeySelective(SysUser record);
}