package com.athen.datasource.spring.boot.autoconfigure;

import com.athen.datasource.spring.boot.autoconfigure.druid.DruidConfig;
import com.athen.datasource.spring.boot.autoconfigure.hikari.HikariCpConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.core.Ordered;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 数据源配置
 * User: chenying
 * Date: 2019-07-12
 * Time: 15:16
 * since: 1.0.0
 */
@Data
@ConfigurationProperties(prefix = "datasource")
public class DataSourceConfig {

    /**
     * 必须设置默认的库,默认master
     */
    private String primary = "master";
    /**
     * 是否启用严格模式,默认不启动.
     * 严格模式下未匹配到数据源直接报错,
     * 非严格模式下则使用默认数据源primary所设置的数据源
     */
    private Boolean strict = false;
    /**
     * 是否使用p6spy输出，默认不输出
     */
    private Boolean p6spy = false;
    /**
     * 每一个数据源
     */
    private Map<String, DataSourceProperty> group = new LinkedHashMap<>();
    /**
     * 多数据源选择算法clazz，默认负载均衡算法
     */
  //  private Class<? extends DynamicDataSourceStrategy> strategy = LoadBalanceDynamicDataSourceStrategy.class;
    /**
     * aop切面顺序，默认优先级最高
     */
    private Integer order = Ordered.HIGHEST_PRECEDENCE;
    /**
     * Druid全局参数配置
     */
    @NestedConfigurationProperty
    private DruidConfig druid = new DruidConfig();
    /**
     * HikariCp全局参数配置
     */
    @NestedConfigurationProperty
    private HikariCpConfig hikari = new HikariCpConfig();
}
