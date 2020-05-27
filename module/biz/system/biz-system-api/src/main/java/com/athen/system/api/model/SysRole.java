package com.athen.system.api.model;

import com.google.common.collect.Lists;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/** 系统角色 --> SYS_ROLE */
@Data
public class SysRole implements Serializable {
    private Long id;

    /** 角色名 --> NAME */
    private String name;

    /** 角色编码 --> CODE */
    private String code;

    /** 角色描述 --> DESCRIPTION */
    private String description;

    /** 创建人 --> CREATE_USER */
    private Long createUser;

    /** 为 "createTime" 提供查询的起始值 */
    private Date createTimeStart;
    /** 为 "createTime" 提供查询的结束值 */
    private Date createTimeEnd;
    /** 创建时间 --> CREATE_TIME */
    private Date createTime;
    //状态
   // private State state;

    private List<SysResource> res= Lists.newArrayList();
}