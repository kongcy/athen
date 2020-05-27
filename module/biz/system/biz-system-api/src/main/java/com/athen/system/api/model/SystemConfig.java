package com.athen.system.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/** 全局配置表 --> SYSTEM_CONFIG */
@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
public class SystemConfig implements Serializable {

    private Long id;

    /** 键 --> CON_KEY */
    private String conKey;

    /** 值 --> CON_VALUE */
    private String conValue;

    /** 说明 --> CON_COMMENT */
    private String conComment;

    public SystemConfig(String key, String value, String comment) {
        conKey = key;
        conValue = value;
        conComment = comment;
    }

}