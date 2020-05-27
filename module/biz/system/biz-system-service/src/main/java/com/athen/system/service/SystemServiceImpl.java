package com.athen.system.service;
import com.athen.core.encrypt.Encrypt;
import com.athen.core.util.A;
import com.athen.core.util.U;
import com.athen.core.vo.DataTable;
import com.athen.core.vo.JsTree;
import com.athen.core.vo.JsTreeState;
import com.athen.dubbo.annotation.FService;
import com.athen.exception.Util.Assert;
import com.athen.system.api.model.*;
import com.athen.system.api.model.enums.State;
import com.athen.system.api.service.SystemService;
import com.athen.system.repository.*;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.athen.core.Constant.DUBBO_VERSION;

@Component
@FService(version = DUBBO_VERSION, group = "athenSystem", interfaceClass = SystemService.class)
@Transactional(readOnly = true)
public class SystemServiceImpl implements SystemService {

    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private SysUserRoleMapper userRoleMapper;
    @Autowired
    private SysResourceMapper resourceMapper;
    @Autowired
    private SysRoleResourceMapper roleResourceMapper;

    @Override
    public SysUser login(String loginName, String password) {
        Assert.assertAccountException(loginName, "用戶名不能为空!");
        Assert.assertAccountException(password, "用户密码不能为空!");
        SysUser user = findUserByLoginName(loginName);
        Assert.assertAccountException(user, "该用户不存在!");
        Assert.assertFalseAccountException(user.getPassword().equals(Encrypt.toMd5(password)), "登陆密码错误!");
        return user;
    }

    @Override
    public SysUser findUserByLoginName(String loginName) {
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andLoginNameEqualTo(loginName);
        return A.first(userMapper.selectByExample(example, new PageBounds(1)));
    }
    @Override
    public DataTable<SysUser> findPageUserByCondition(DataTable<SysUser> dt, SysUser user) {
        PageBounds page = new PageBounds(dt.pageNo(), dt.getiDisplayLength());
        SysUserExample example = null;
        if (user != null) {
            example = new SysUserExample();
            SysUserExample.Criteria criteria = example.or();
            if (U.isNotBlank(user.getLoginName())) {
                criteria.andLoginNameLike(U.like(user.getLoginName()));
            }
            if (U.isNotBlank(user.getUserName())) {
                criteria.andUserNameLike(U.like(user.getUserName()));
            }
            if (U.isNotBlank(user.getEmail())) {
                criteria.andEmailLike(U.like(user.getEmail()));
            }
            if (U.isNotBlank(user.getPhone())) {
                criteria.andPhoneLike(U.like(user.getPhone()));
            }
            if (U.isNotBlank(user.getState())) {
                criteria.andStateEqualTo(user.getState().getCode());
            }
        }
        List<SysUser> users = userMapper.selectByExample(example, page);
        dt.setAaData(users);
        dt.setiTotalDisplayRecords(((PageList) users).getPaginator().getTotalCount());
        return dt;
    }

    @Override
    public SysUser findUserById(Long id) {
        SysUser user = userMapper.selectByPrimaryKey(id);
        if (user != null) {
            List<SysRole> roles = findRoleByUser(id);
            if (A.isEmpty(roles))
                return user;
            user.setRoles(roles);
        }
        return user;
    }

    @Transactional(readOnly = false)
    @Override
    public void saveUser(SysUser user, Long[] ids) {
        if (U.isNotBlank(user.getPlainPassword()))
            user.setPassword(Encrypt.toMd5(user.getPlainPassword()));
        if (U.less0(user.getId())) { // new user
            user.setCreateTime(new Date());
            if (userMapper.insertSelective(user) > 0)
                saveUserRole(user.getId(), ids);
        } else {
            if (A.isNotEmpty(user.getRoles()))
                userRoleMapper.deleteRoleByUserId(user.getId());
            userMapper.updateByPrimaryKeySelective(user);
            saveUserRole(user.getId(), ids);
        }
    }

    @Override
    public void saveUserRole(Long userId, Long[] ids) {
        if (ids != null) {
            for (Long id : ids) {
                SysUserRole ur = new SysUserRole();
                ur.setUserId(userId);
                ur.setRoleId(id);
                userRoleMapper.insertSelective(ur);
            }
        }
    }

    @Override
    public boolean checkUserByLoginName(String loginName) {
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andLoginNameEqualTo(loginName);
        List<SysUser> users = userMapper.selectByExample(example);
        return A.isEmpty(users);
    }

    @Transactional(readOnly = false)
    @Override
    public boolean deleteUserById(Long id) {
        if (userMapper.deleteByPrimaryKey(id) > 0) {
            if (userRoleMapper.deleteRoleByUserId(id) > 0)
                return true;
        }
        return false;
    }

    @Override
    public List<SysRole> findAll() {
        return roleMapper.selectByExample(null);
    }

    @Override
    public List<SysRole> findRoleByUser(Long userId) {
        List<Long> rIds = userRoleMapper.findRoleByUserId(userId);
        if (A.isEmpty(rIds))
            return null;
        SysRoleExample example = new SysRoleExample();
        SysRoleExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(rIds);
        return roleMapper.selectByExample(example);
    }

    @Override
    public DataTable<SysRole> finsPageRoleByCondition(DataTable<SysRole> dt, SysRole role) {
        PageBounds page = new PageBounds(dt.pageNo(), dt.getiDisplayLength());
        SysRoleExample example = null;
        if (role != null) {
            example = new SysRoleExample();
            SysRoleExample.Criteria criteria = example.or();
            if (U.isNotBlank(role.getName())) {
                criteria.andNameLike(U.like(role.getName()));
            }
            if (U.isNotBlank(role.getCode())) {
                criteria.andCodeLike(U.like(role.getCode()));
            }
        }
        List<SysRole> roles = roleMapper.selectByExample(example, page);
        dt.setAaData(roles);
        dt.setiTotalDisplayRecords(((PageList) roles).getPaginator().getTotalCount());
        return dt;
    }

    @Override
    public DataTable<SysUser> findPageUserByRoleId(DataTable<SysUser> dt, Long roleId) {
        List<Long> uids = roleMapper.findUserByRole(roleId);
        if (A.isNotEmpty(uids)) {
            PageBounds pageBounds = new PageBounds(dt.pageNo(), dt.getiDisplayLength());
            SysUserExample example = new SysUserExample();
            example.createCriteria().andIdIn(uids);
            List<SysUser> users = userMapper.selectByExample(example, pageBounds);
            dt.setiTotalDisplayRecords(((PageList) users).getPaginator().getTotalCount());
            dt.setAaData(users);
        }
        return dt;
    }

    @Override
    public List<JsTree> getResourceTree() {
        SysResourceExample example = new SysResourceExample();
        example.setOrderByClause("supId");
        example.setOrderByClause("seq");
        List<SysResource> list = resourceMapper.selectByExample(example);
        List<JsTree> res = Lists.newArrayList();
        for (SysResource r : list) {
            JsTree jt = new JsTree();
            jt.setId(r.getId().toString());
            jt.setParent(r.getSupId() == null ? "#" : (r.getSupId().compareTo(0L) > 0 ? r.getSupId().toString() : "#"));
            jt.setText(r.getName());
            jt.setIcon(r.getIcon());
            res.add(jt);
        }
        return res;
    }

    @Transactional(readOnly = false)
    @Override
    public void saveRole(SysRole role, List<Long> ids) {
        if (U.less0(role.getId())) {
            role.setCreateTime(new Date());
            if (roleMapper.insertSelective(role) > 0)
                saveRoleRes(role.getId(), ids);
        } else {
            roleResourceMapper.deleteRoleResByRoleId(role.getId());
            roleMapper.updateByPrimaryKeySelective(role);
            saveRoleRes(role.getId(), ids);
        }

    }

    private void saveRoleRes(Long roleId, List<Long> ids) {
        if (ids != null) {
            for (Long id : ids) {
                SysRoleResource rs = new SysRoleResource();
                rs.setRoleId(roleId);
                rs.setResId(id);
                roleResourceMapper.insertSelective(rs);
            }
        }
    }

    @Override
    public SysRole findRoleById(Long id) {
        SysRole role = roleMapper.selectByPrimaryKey(id);
        if (role != null) {
            List<SysResource> res = findResByRoleId(id);
            if (A.isNotEmpty(res)) {
                role.setRes(res);
            }
        }
        return role;
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteRoleById(Long id) {
        if (roleMapper.deleteByPrimaryKey(id) > 0) {
            SysRoleResourceExample se = new SysRoleResourceExample();
            se.or().andRoleIdEqualTo(id);
            roleResourceMapper.deleteByExample(se);
        }

    }

    @Override
    public List<SysResource> findResByRoleId(Long roleId) {
        List<Long> res = roleResourceMapper.findRoleResByRoleId(roleId);
        if (A.isEmpty(res))
            return null;
        SysResourceExample example = new SysResourceExample();
        SysResourceExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(res);
        return resourceMapper.selectByExample(example);
    }

    @Override
    public List<JsTree> GetResourceTreeHasSelect(SysRole role) {
        {
            Map<Long, Long> map = Maps.newHashMap();
            for (SysResource sr : role.getRes()) {
                map.put(sr.getId(), sr.getId());
            }
            SysResourceExample example = new SysResourceExample();
            example.setOrderByClause("DEPTH DESC");
            List<SysResource> list = resourceMapper.selectByExample(example);
            List<JsTree> jts = Lists.newArrayList();
            Map<Long, Long> subMap = Maps.newHashMap();
            for (SysResource kr : list) {
                JsTree jt = new JsTree();
                jt.setId(kr.getId().toString());
                jt.setParent(
                        kr.getSupId() == null ? "#" : (kr.getSupId().compareTo(0L) > 0 ? kr.getSupId().toString() : "#"));
                jt.setText(kr.getName());
                JsTreeState jtState = new JsTreeState();
                // 一级节点除了主页,都是未选中状态
                if ((kr.getSupId() == null || 0L == kr.getSupId()) && !"main".equalsIgnoreCase(kr.getCode()) && SysResource.ResourceType.MENU.equals(kr.getType())) {
                    jtState.setSelected(false);
                } else {
                    if (subMap.size() > 0 && subMap.get(kr.getId()) != null) {
                        jtState.setSelected(false);
                    } else {
                        jtState.setSelected((map.get(kr.getId()) != null));
                    }
                    if (kr.getDepth() != null && kr.getDepth() > 2 && jtState.getSelected()) {
                        subMap.put(kr.getSupId(), kr.getId());
                    }
                }
                jt.setState(jtState);
                jts.add(jt);
            }
            return jts;
        }
    }

    @Override
    public SysResource findResById(Long id) {
        SysResource res = resourceMapper.selectByPrimaryKey(id);
        if (res != null && U.isNotBlank(res.getMethod())) {
            String[] ms = res.getMethod().split(",");
            res.setMethods(Arrays.asList(ms));
        }
        return res;
    }

    @Transactional(readOnly = false)
    @Override
    public boolean saveResource(SysResource res) {
        if (U.greater0(res.getId())) {
            if (res.getSupId().compareTo(0L) > 0) {
                SysResource supRes = resourceMapper.selectByPrimaryKey(res.getSupId());
                res.setPath(supRes.getPath() + res.getId() + ".");
                res.setDepth(supRes.getDepth() + 1);
            } else {
                res.setPath(res.getId() + ".");
                res.setDepth(1);
            }
        } else {
            res.setCreateTime(new Date());
            res.setState(State.Enable);
            resourceMapper.insertSelective(res);
            if (res.getSupId().compareTo(0L) > 0) {
                SysResource supZr = resourceMapper.selectByPrimaryKey(res.getSupId());
                res.setPath(supZr.getPath() + res.getId() + ".");
                res.setDepth(supZr.getDepth() + 1);
            } else {
                res.setPath(res.getId() + ".");
                res.setDepth(1);
            }
        }
        if (resourceMapper.updateByPrimaryKeySelective(res) > 0)
            return true;
        return false;
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteResource(Long id) {
        {
            SysResource resource = resourceMapper.selectByPrimaryKey(id);
            SysResourceExample example;
            List<Long> ids;
            if (resource != null) {
                ids = Lists.newArrayList(id);
                example = new SysResourceExample();
                if (U.less0(resource.getSupId()))
                    example.or().andPathLike(U.rightLike(resource.getId().toString()));
                else
                    example.or().andSupIdEqualTo(id);
                List<SysResource> res = resourceMapper.selectByExample(example);
                for (SysResource r : res) {
                    ids.add(r.getId());
                }
                example.clear();
                example.or().andIdIn(ids);
                if (resourceMapper.deleteByExample(example) > 0) {
                    SysRoleResourceExample se = new SysRoleResourceExample();
                    se.or().andResIdIn(ids);
                    roleResourceMapper.deleteByExample(se);
                }
            }
        }
    }

    @Override
    public List<Resource> caseResource(Long userId) {

        if (userId == null || userId.compareTo(0L) <= 0)
            return Lists.newArrayList();
        Map<Long, List<Resource>> map = Maps.newHashMap();
        List<Long> rIds = roleResourceMapper.findResIdByUser(userId);
        if (A.isEmpty(rIds))
            return Lists.newArrayList();
        SysResourceExample example = new SysResourceExample();
        SysResourceExample.Criteria criteria = example.createCriteria();
        criteria.andStateEqualTo(State.Enable.getCode());
        //criteria.andTypeEqualTo(SysResource.ResourceType.MENU.name());
        criteria.andIdIn(rIds);
        List<SysResource> res = resourceMapper.selectByExample(example);
        Resource r;
        for (SysResource kr : res) {
            List<Resource> re = map.get(kr.getSupId());
            if (re == null) {
                re = Lists.newArrayList();
            }
            r = new Resource();
            BeanUtils.copyProperties(kr, r);
            re.add(r);
            map.put(kr.getSupId(), re);
        }
        return MakeResource(map, 0L);

    }

    private List<Resource> MakeResource(Map<Long, List<Resource>> map, Long supId) {
        List<Resource> res = Lists.newArrayList();
        List<Resource> reList = map.get(supId);
        if (reList != null) {
            for (Resource re : reList) {
                re.setChildren(MakeResource(map, re.getId()));
                res.add(re);
            }
        }
        return res;
    }

    @Transactional(readOnly = false)
    @Override
    public int updateUser(SysUser user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public DataTable<SysResource> findPageResByCondition(DataTable<SysResource> dt, SysResource res) {
        List<Long> resId = null;
        if (U.greater0(res.getId())) {
            resId = roleResourceMapper.findRoleResByRoleId(res.getId());
        }
        if (A.isEmpty(resId))
            return dt;
        SysResourceExample example = new SysResourceExample();
        SysResourceExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(resId);
        List<SysResource> resources = resourceMapper.selectByExample(example, new PageBounds(dt.pageNo(), dt.getiDisplayLength()));
        dt.setAaData(resources);
        dt.setiTotalDisplayRecords(((PageList) resources).getPaginator().getTotalCount());
        return dt;
    }

}
