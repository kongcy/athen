package com.athen.datasource.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * mybaties 枚举转换类
 * @author chenying
 * @date 2019-07-13 10:30
 */
@Data
@ConfigurationProperties(prefix = "handler")
public class HandlerEntity implements Serializable {
    /**包名**/
    private String packagesName;

}
