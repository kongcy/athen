package com.athen.config.model;

import lombok.Data;

import java.io.Serializable;

/** no comment on table --> config_table */
@Data
public class ConfigTable implements Serializable {
    private Long id;

    /** 应用名，GLOBAL为全局 --> application */
    private String application="";

    /** 应用模块名(system,order...) --> module */
    private String module="";

    /** key值 --> key */
    private String key="";

    /** 对应value值 --> value */
    private String value="";

    /** 描述 --> desc */
    private String desc="";

    private static final long serialVersionUID = 1L;
}