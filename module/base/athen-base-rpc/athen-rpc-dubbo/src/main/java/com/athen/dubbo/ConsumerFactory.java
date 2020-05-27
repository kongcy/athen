package com.athen.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.athen.dubbo.core.ReferenceConfig;
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * 消费者工厂类
 * User: chenying
 * Date: 2019-08-07
 * Time: 11:38
 * since: 1.0.0
 */
@Slf4j
public class ConsumerFactory {

    private static ApplicationConfig application = null;
    private static RegistryConfig registry = null;
    private static ConcurrentSkipListMap<String,Object> beanCache = new ConcurrentSkipListMap<String,Object>();

    static {
        // dubbo环境初始化
        if (application == null) {
            application = new ApplicationConfig();
            String applicationName = "";
            application.setName(applicationName);
            // 连接注册中心配置
            registry = new RegistryConfig();
           // registry.setAddress(RegistryFsdfConfig.getFsdfDefaulRegistryAddress());
            // registry.setTimeout(3000);
        }
    }
    public Object getConsumer(String interfaceName, String group, String version, Class baseInterface,
                              String target, int connections){
        return null;
    }


    private static Object dubboConsumerBean(String interfaceName, String group, String version, Class baseInterface,int connections){
        // 引用远程服务
        ReferenceConfig reference = new ReferenceConfig(); // 该实例很重量，里面封装了所有与注册中心及服务提供方连接，请缓存

        reference.setVersion(version);
        reference.setGroup(group);
        reference.setApplication(application);
        reference.setRegistry(registry);
        int timeout=0;
       /* int timeout=RegistryFsdfConfig.getFsdfClientConnectTimeout(interfaceName);
        if(timeout>0){
            log.info("自定义接口超时，interface={},timeout={}",interfaceName,timeout);
        }else{
            timeout=RegistryFsdfConfig.getFsdfClientConnectTimeout();
        }*/
        reference.setTimeout(timeout);
        reference.setCheck(false);
        // 重试次数，禁止重试
        reference.setRetries(0);
        reference.setConnections(connections);
       // reference.setInterface(c);
        //改用缓存对象
       // Object ret = ReferenceConfigCache.getCache().get(reference);
        return null;
    }



}
