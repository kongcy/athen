package com.athen.system.api.model;

import com.athen.system.api.model.enums.State;
import com.google.common.collect.Lists;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/** 系统用户 --> SYS_USER */
@Data
public class SysUser implements Serializable {
    private Long id;

    /** 登录账号 --> LOGIN_NAME */
    private String loginName;

    /** 登录密码 --> PASSWORD */
    private String password;

    /** 用户名 --> USER_NAME */
    private String userName;

    /** 手机号 --> PHONE */
    private String phone;

    /** 邮箱 --> EMAIL */
    private String email;

    /** 状态 --> STATE */
    private State state;

    /** 登录IP --> LOGIN_IP */
    private Integer loginIp;

    /** 为 "createTime" 提供查询的起始值 */
    private Date createTimeStart;
    /** 为 "createTime" 提供查询的结束值 */
    private Date createTimeEnd;
    /** 创建时间 --> CREATE_TIME */
    private Date createTime;

    /** 创建人 --> CREATE_ID */
    private Long createId;
    
    private List<SysRole> roles = Lists.newArrayList();

    private String plainPassword;

    private List<Resource> res;
}