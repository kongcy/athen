package com.athen.system.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/** 资源角色 --> SYS_ROLE_RESORCE */
@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
public class SysRoleResource implements Serializable {
    private Long id;

    /** 角色ID --> ROLE_ID */
    private Long roleId;

    /** 资源ID --> RES_ID */
    private Long resId;
}