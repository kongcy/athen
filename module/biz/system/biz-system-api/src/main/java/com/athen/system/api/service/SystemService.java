package com.athen.system.api.service;

import java.util.List;

import com.athen.core.vo.DataTable;
import com.athen.core.vo.JsTree;
import com.athen.system.api.model.Resource;
import com.athen.system.api.model.SysResource;
import com.athen.system.api.model.SysRole;
import com.athen.system.api.model.SysUser;
/**
 * 系统服务类
 * **/
public interface SystemService {

	/** 用户登陆 **/
	public SysUser login(String loginName, String password);

	/** 获取用户信息通过用户名 **/
	public SysUser findUserByLoginName(String loginName);

	/** 根据查询条件获取用户列表 */
	public DataTable<SysUser> findPageUserByCondition(DataTable<SysUser> dt, SysUser user);

	/** 查找用户通过ID **/
	public SysUser findUserById(Long id);

	/** 保存用户 ***/
	public void saveUser(SysUser user, Long[] ids);

	/** 保存用户角色表 ***/
	public void saveUserRole(Long userId, Long[] ids);

	/** 检查登陆名是否已存在 **/
	public boolean checkUserByLoginName(String loginName);

	/** 删除用户 **/
	public boolean deleteUserById(Long id);

	/** 获取所有角色 **/
	public List<SysRole> findAll();

	/** 获取用户的角色 **/
	public List<SysRole> findRoleByUser(Long userId);

	/** 根据查询条件获取角色列表 **/
	public DataTable<SysRole> finsPageRoleByCondition(DataTable<SysRole> dt, SysRole role);

	/** 通过角色ID查找所有用户列表 ***/
	public DataTable<SysUser> findPageUserByRoleId(DataTable<SysUser> dt, Long roleId);

	/** 获取所有资料列表 **/
	public List<JsTree> getResourceTree();

	/** 保存角色 **/
	public void saveRole(SysRole role, List<Long> ids);

	/** 获取角色信息 **/
	public SysRole findRoleById(Long id);

	/** 删除角色 **/
	public void deleteRoleById(Long id);

	/** 获取角色资源 **/
	public List<SysResource> findResByRoleId(Long roleId);

	/** 获取角色下所有的资源 ***/
	public List<JsTree> GetResourceTreeHasSelect(SysRole role);

	/** 获取菜单资源 **/
	public SysResource findResById(Long id);

	/** 保存资源 **/
	public boolean saveResource(SysResource res);

	/** 删除资源，级联删除子资源 **/
	public void deleteResource(Long id);

	/** 获取用户资源 **/
	public List<Resource> caseResource(Long userId);

	/** 更新用户信息 **/
	public int updateUser(SysUser user);

	/** 根据查询条件获取资源列表 **/
	public DataTable<SysResource> findPageResByCondition(DataTable<SysResource> dt, SysResource res);

}
