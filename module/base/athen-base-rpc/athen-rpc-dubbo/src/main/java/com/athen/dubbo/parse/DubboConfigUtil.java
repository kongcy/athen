package com.athen.dubbo.parse;

import com.alibaba.dubbo.config.*;
import com.athen.config.util.PropertyUtils;
import com.athen.dubbo.model.DubboProperties;
import org.springframework.core.env.Environment;

import java.util.*;

/**
 * @author chenying
 * @data 2019-06-16 18:58
 */
public class DubboConfigUtil {
    protected static Map<String, Map<String, AbstractConfig>> ID_CONFIG_MAP = null;

    protected static String APPLICATIONS_KEY = "applications";
    protected static String MODULES_KEY = "modules";
    protected static String REGISTRIES_KEY = "registries";
    protected static String PROTOCOLS_KEY = "protocols";
    protected static String MONITORS_KEY = "monitors";
    protected static String PROVIDERS_KEY = "providers";
    protected static String CONSUMERS_KEY = "consumers";

    private DubboConfigUtil(){}
    /**
     * 初始化配置
     * @param  properties
     **/
    public static void initConfig(DubboProperties properties) {
         if(ID_CONFIG_MAP!=null) return;
         synchronized (DubboConfigUtil.class){
             if(ID_CONFIG_MAP!=null){
                 return;
             }
             ID_CONFIG_MAP = new HashMap<>();
             ID_CONFIG_MAP.put(APPLICATIONS_KEY,getApplicationMap(properties));
             ID_CONFIG_MAP.put(MODULES_KEY,getModuleMap(properties));
             ID_CONFIG_MAP.put(REGISTRIES_KEY,getRegistryMap(properties));
             ID_CONFIG_MAP.put(PROTOCOLS_KEY,getProtocolMap(properties));
             ID_CONFIG_MAP.put(MONITORS_KEY,getMonitorMap(properties));
             ID_CONFIG_MAP.put(PROVIDERS_KEY,getProviderMap(properties));
             ID_CONFIG_MAP.put(CONSUMERS_KEY,getConsumerMap(properties));
         }
    }

    /**
     * 解析 ApplicationConfig
     * @param application
     * @param properties
     * @param environment
     * @param errors
     */
    public static ApplicationConfig parseApplication(String application, DubboProperties properties, Environment environment, String... errors) {
        ApplicationConfig applicationConfig = null;
        if (application == null || "".equals(application)) {
            applicationConfig = properties.getApplication();
            if (applicationConfig == null) {
                applicationConfig = new ApplicationConfig();
                applicationConfig.setName(PropertyUtils.getStrPropertyValue("dubbo.provider.name"));
               // applicationConfig.setName(environment.getProperty("spring.application.name"));
            }
        } else {
            application = environment.resolvePlaceholders(application);
            Map<String, AbstractConfig> applicationMap = ID_CONFIG_MAP.get(APPLICATIONS_KEY);
            applicationConfig = (ApplicationConfig) applicationMap.get(application);
            if (applicationConfig == null) {
                applicationConfig = properties.getApplications().get(application);
                if (applicationConfig == null) {
                    throw new NullPointerException(buildErrorMsg(errors));
                }
            }
        }
        return applicationConfig;
    }

    /**
     * 解析 ModuleConfig
     * @param module
     * @param properties
     * @param environment
     * @param errors
     **/
    public static ModuleConfig parseModule(String module, DubboProperties properties, Environment environment, String... errors) {
        ModuleConfig moduleConfig = null;
        if (module == null || "".equals(module)) {
            moduleConfig = properties.getModule();
        } else {
            module = environment.resolvePlaceholders(module);
            Map<String, AbstractConfig> moduleMap = ID_CONFIG_MAP.get(MODULES_KEY);
            moduleConfig = (ModuleConfig) moduleMap.get(module);
            if (moduleConfig == null) {
                moduleConfig = properties.getModules().get(module);
                if (moduleConfig == null) {
                    throw new NullPointerException(buildErrorMsg(errors));
                }
            }
        }
        return moduleConfig;
    }

    /**
     * 解析注册中心
     * @param registries
     * @param properties
     * @param environment
     * @param errors
     * **/
    public static List<RegistryConfig> parseRegistries(String[] registries, DubboProperties properties, Environment environment, String... errors) {
        List<RegistryConfig> registryList = new ArrayList<>();
        if (registries == null || registries.length == 0) {
            RegistryConfig registry;
            registry= properties.getRegistry();
            if (registry != null) {
                registryList.add(registry);
            }else{
                registry = new RegistryConfig();
                String port = PropertyUtils.getStrPropertyValue("dubbo.protocol.port");
                registry.setPort(Integer.parseInt(port));
                registryList.add(registry);
            }
        } else {
            for (int i = 0, len = registries.length; i < len; i++) {
                registries[i] = environment.resolvePlaceholders(registries[i]);
            }
            registryList = new ArrayList<RegistryConfig>();
            Map<String, AbstractConfig> registryMap = ID_CONFIG_MAP.get(REGISTRIES_KEY);
            for (String registry : registries) {
                RegistryConfig registryConfig = (RegistryConfig) registryMap.get(registry);
                if (registryConfig == null) {
                    registryConfig = properties.getRegistries().get(registry);
                    if (registryConfig == null) {
                        List<String> errorList = new ArrayList<String>();
                        if (errors != null) {
                            for (String error : errors) {
                                errorList.add(error);
                            }
                        }
                        errorList.add(registry);
                        throw new NullPointerException(buildErrorMsg(errorList.toArray(new String[0])));
                    }
                }
                registryList.add(registryConfig);
            }
        }
        return registryList;
    }

    /**
     * 解析端口配置
     * @param protocols
     * @param properties
     * @param environment
     * @param errors
     **/
    public static List<ProtocolConfig> parseProtocols(String[] protocols, DubboProperties properties, Environment environment, String... errors) {
        List<ProtocolConfig> protocolList = null;
        if (protocols != null && protocols.length == 0) {
            ProtocolConfig protocol = properties.getProtocol();
            if (protocol != null) {
                protocolList = new ArrayList<ProtocolConfig>();
                protocolList.add(protocol);
            }
        } else {
            for (int i = 0, len = protocols.length; i < len; i++) {
                protocols[i] = environment.resolvePlaceholders(protocols[i]);
            }
            protocolList = new ArrayList<ProtocolConfig>();
            Map<String, AbstractConfig> protocolMap = ID_CONFIG_MAP.get(PROTOCOLS_KEY);
            for (String protocol : protocols) {
                ProtocolConfig protocolConfig = (ProtocolConfig) protocolMap.get(protocol);
                if (protocolConfig == null) {
                    protocolConfig = properties.getProtocols().get(protocol);
                    if (protocolConfig == null) {
                        List<String> errorList = new ArrayList<String>();
                        if (errors != null) {
                            for (String error : errors) {
                                errorList.add(error);
                            }
                        }
                        errorList.add(protocol);
                        throw new NullPointerException(buildErrorMsg(errorList.toArray(new String[0])));
                    }
                }
                protocolList.add(protocolConfig);
            }
        }
        return protocolList;
    }

    /**
     * @param monitor
     * @param properties
     * @param environment
     * @param errors
     *
     * **/
    public static MonitorConfig parseMonitor(String monitor, DubboProperties properties, Environment environment, String... errors) {
        MonitorConfig monitorConfig = null;
        if (monitor == null || "".equals(monitor)) {
            monitorConfig = properties.getMonitor();
        } else {
            monitor = environment.resolvePlaceholders(monitor);
            Map<String, AbstractConfig> monitorMap = ID_CONFIG_MAP.get(MONITORS_KEY);
            monitorConfig = (MonitorConfig) monitorMap.get(monitor);
            if (monitorConfig == null) {
                monitorConfig = properties.getMonitors().get(monitor);
                if (monitorConfig == null) {
                    throw new NullPointerException(buildErrorMsg(errors));
                }
            }
        }
        return monitorConfig;
    }

    /**
     * 解析提供者配置
     * @param provider
     * @param properties
     * @param environment
     * @param errors
     * **/
    public static ProviderConfig parseProvider(String provider, DubboProperties properties, Environment environment, String... errors) {
        ProviderConfig providerConfig = null;
        if (provider == null || "".equals(provider)) {
            providerConfig = properties.getProvider();
        } else {
            provider = environment.resolvePlaceholders(provider);
            Map<String, AbstractConfig> providerMap = ID_CONFIG_MAP.get(PROVIDERS_KEY);
            providerConfig = (ProviderConfig) providerMap.get(provider);
            if (providerConfig == null) {
                providerConfig = properties.getProviders().get(provider);
                if (providerConfig == null) {
                    throw new NullPointerException(buildErrorMsg(errors));
                }
            }
        }
        return providerConfig;
    }


    /**
     * 解析消费者配置
     * @param consumer
     * @param properties
     * @param environment
     * @param errors
     *
     * **/
    public static ConsumerConfig parseConsumer(String consumer, DubboProperties properties, Environment environment, String... errors) {
        ConsumerConfig consumerConfig = null;
        if (consumer == null || "".equals(consumer)) {
            consumerConfig = properties.getConsumer();
        } else {
            consumer = environment.resolvePlaceholders(consumer);
            Map<String, AbstractConfig> consumerMap = ID_CONFIG_MAP.get(CONSUMERS_KEY);
            consumerConfig = (ConsumerConfig) consumerMap.get(consumer);
            if (consumerConfig == null) {
                consumerConfig = properties.getConsumers().get(consumer);
                if (consumerConfig == null) {
                    throw new NullPointerException(buildErrorMsg(errors));
                }
            }
        }
        return consumerConfig;
    }


    private static Map<String,AbstractConfig> getApplicationMap(DubboProperties properties){
        Map<String,AbstractConfig> applicationMap = new LinkedHashMap<>();
        Map<String,ApplicationConfig> applications = properties.getApplications();
        if(applications!=null){
            for(Map.Entry<String,ApplicationConfig> application:applications.entrySet()){
                ApplicationConfig applicationConfig = application.getValue();
                applicationMap.put(applicationConfig.getId(),applicationConfig);
            }
        }
        return applicationMap;
    }
    private  static Map<String,AbstractConfig> getModuleMap(DubboProperties properties){
        Map<String,AbstractConfig> moduleMap = new LinkedHashMap<>();
        Map<String,ModuleConfig> modules = properties.getModules();
        if(modules!=null){
            for(Map.Entry<String,ModuleConfig> module:modules.entrySet()){
                ModuleConfig moduleConfig = module.getValue();
                moduleMap.put(moduleConfig.getId(),moduleConfig);
            }
        }
        return  moduleMap;
    }
    private static Map<String,AbstractConfig> getRegistryMap(DubboProperties properties){
        Map<String,AbstractConfig> registryMap = new LinkedHashMap<>();
        Map<String,RegistryConfig> registries = properties.getRegistries();
        if(registries!=null){
            for(Map.Entry<String,RegistryConfig> registry:registries.entrySet()){
                RegistryConfig registryConfig = registry.getValue();
                registryMap.put(registryConfig.getId(),registryConfig);
            }
        }
        return registryMap;
    }
    private static Map<String,AbstractConfig> getProtocolMap(DubboProperties properties){
        Map<String,AbstractConfig> protocolMap = new LinkedHashMap<>();
        Map<String,ProtocolConfig> protocols = properties.getProtocols();
        if(protocols!=null){
            for(Map.Entry<String,ProtocolConfig> protocol:protocols.entrySet()){
                ProtocolConfig protocolConfig = protocol.getValue();
                protocolMap.put(protocolConfig.getId(),protocolConfig);
            }
        }
        return protocolMap;
    }
    private static Map<String,AbstractConfig> getMonitorMap(DubboProperties properties){
        Map<String,AbstractConfig> monitorMap = new LinkedHashMap<>();
        Map<String,MonitorConfig> monitors = properties.getMonitors();
        if(monitors!=null){
            for(Map.Entry<String,MonitorConfig> monitor:monitors.entrySet()){
                MonitorConfig monitorConfig = monitor.getValue();
                monitorMap.put(monitorConfig.getId(),monitorConfig);
            }
        }
        return monitorMap;
    }
    private static Map<String,AbstractConfig> getProviderMap(DubboProperties properties){
        Map<String,AbstractConfig> providerMap = new LinkedHashMap<>();
        Map<String,ProviderConfig> providers = properties.getProviders();
        if(providers!=null){
            for(Map.Entry<String,ProviderConfig> provider:providers.entrySet()){
                ProviderConfig providerConfig = provider.getValue();
                providerMap.put(providerConfig.getId(),providerConfig);
            }
        }
        return providerMap;
    }
    private static Map<String,AbstractConfig> getConsumerMap(DubboProperties properties){
        Map<String,AbstractConfig> consumerMap = new LinkedHashMap<>();
        Map<String,ConsumerConfig> consumers = properties.getConsumers();
        if(consumers!=null){
            for(Map.Entry<String,ConsumerConfig> consumer:consumers.entrySet()){
                ConsumerConfig consumerConfig = consumer.getValue();
                consumerMap.put(consumerConfig.getId(),consumerConfig);
            }
        }
        return consumerMap;
    }

    public static String buildErrorMsg(String... errors) {
        if (errors == null || errors.length != 3) {
            throw new UnsupportedOperationException("Not support");
        }
        return new StringBuilder().append("beanName=").append(errors[0]).append(", ").append(errors[1])
                .append("=").append(errors[2]).append(" not found in multi configs").toString();
    }
}
