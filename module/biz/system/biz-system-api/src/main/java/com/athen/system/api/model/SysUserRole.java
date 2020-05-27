package com.athen.system.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/** 用户角色 --> SYS_USER_ROLE */
@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
public class SysUserRole implements Serializable {
    private Long id;

    /** 用户ID --> USER_ID */
    private Long userId;

    /** 角色ID --> ROLE_ID */
    private Long roleId;
}