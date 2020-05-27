package com.athen.system.api.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/** 系统日志表 --> SYSTEM_LOG */
@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
public class SystemLog implements Serializable {
    private Long id;

    /** 操作人ID --> USER_ID */
    private Long userId;

    /** 操作人名 --> USER_NAME */
    private String userName;

    /** 访问地址 --> IP */
    private String ip;

    /** 操作内容. 长度在 500 个字以内 --> CONTENT */
    private String content;

    /** 为 "createTime" 提供查询的起始值 */
    private Date createTimeStart;
    /** 为 "createTime" 提供查询的结束值 */
    private Date createTimeEnd;
    /** 创建时间 --> CREATE_TIME */
    private Date createTime;
}