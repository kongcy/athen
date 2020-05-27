package com.athen.dubbo.model;

import com.alibaba.dubbo.config.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author chenying
 * @data 2019-06-16 17:46
 * spring-boot dubbo model
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "spring.dubbo")
public class DubboProperties {
        /**
         * Indicates dubbo server
         */
        private boolean server;

        /**
         * {@link ApplicationConfig} property
         */
        private ApplicationConfig application;

        /**
         * {@link ModuleConfig} property
         */
        private ModuleConfig module;

        /**
         * {@link RegistryConfig} property
         */
        private RegistryConfig registry;

        /**
         * {@link ProtocolConfig} property
         */
        private ProtocolConfig protocol;

        /**
         * {@link MonitorConfig} property
         */
        private MonitorConfig monitor;

        /**
         * {@link ProviderConfig} property
         */
        private ProviderConfig provider;

        /**
         * {@link ConsumerConfig} property
         */
        private ConsumerConfig consumer;

        /**
         * Multiple {@link ApplicationConfig} property
         */
        private Map<String, ApplicationConfig> applications =
                new LinkedHashMap<String, ApplicationConfig>();

        /**
         * Multiple {@link ModuleConfig} property
         */
        private Map<String, ModuleConfig> modules = new LinkedHashMap<String, ModuleConfig>();

        /**
         * Multiple {@link RegistryConfig} property
         */
        private Map<String, RegistryConfig> registries = new LinkedHashMap<String, RegistryConfig>();

        /**
         * Multiple {@link ProtocolConfig} property
         */
        private Map<String, ProtocolConfig> protocols = new LinkedHashMap<String, ProtocolConfig>();

        /**
         * Multiple {@link MonitorConfig} property
         */
        private Map<String, MonitorConfig> monitors = new LinkedHashMap<String, MonitorConfig>();

        /**
         * Multiple {@link ProviderConfig} property
         */
        private Map<String, ProviderConfig> providers = new LinkedHashMap<String, ProviderConfig>();

        /**
         * Multiple {@link ConsumerConfig} property
         */
        private Map<String, ConsumerConfig> consumers = new LinkedHashMap<String, ConsumerConfig>();
        /**是否启用配置**/
        private boolean isConfig;

}
