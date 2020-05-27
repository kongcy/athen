package com.athen.dubbo.config;

import com.athen.core.util.U;
import com.athen.dubbo.annotation.EnableCustomDubboService;
import com.athen.dubbo.annotation.FService;
import com.athen.dubbo.core.ServiceBean;
import com.athen.dubbo.model.DubboProperties;
import com.athen.dubbo.parse.DubboConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @author chenying
 * @data 2019-06-22 23:30
 */
@Configuration
@ConditionalOnClass(FService.class)
@ConditionalOnBean(annotation = EnableCustomDubboService.class)
@AutoConfigureAfter(DubboAutoConfiguration.class)
@EnableConfigurationProperties(DubboProperties.class)
public class DubboProviderAutoConfiguration {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private DubboProperties properties;

    @PostConstruct
    public void init() throws Exception {
        Map<String, Object> beanMap = this.applicationContext.getBeansWithAnnotation(FService.class);
        if (beanMap != null && beanMap.size() > 0) {
            DubboConfigUtil.initConfig(this.properties);
            for (Map.Entry<String, Object> entry : beanMap.entrySet()) {
                this.initProviderBean(entry.getKey(), entry.getValue());
            }
        }
    }

    private void initProviderBean(String beanName, Object bean) throws Exception {
        FService service = this.applicationContext.findAnnotationOnBean(beanName, FService.class);
        ServiceBean<Object> serviceConfig = new ServiceBean<Object>(service);
        if ((U.isBlank(service.interfaceClass()) || service.interfaceClass() == void.class)
                && (U.isBlank(service.interfaceName()))) {
            Class<?>[] interfaces = bean.getClass().getInterfaces();
            if (interfaces.length > 0) {
                serviceConfig.setInterface(interfaces[0]);
            }
        }
        Environment environment = this.applicationContext.getEnvironment();
        String application = service.application();
        serviceConfig.setApplication(DubboConfigUtil.parseApplication(application, this.properties, environment,
                beanName, "application", application));
        String module = service.module();
        serviceConfig.setModule(
                DubboConfigUtil.parseModule(module, this.properties, environment, beanName, "module", module));
        String[] registries = service.registry();
        serviceConfig.setRegistries(
                DubboConfigUtil.parseRegistries(registries, this.properties, environment, beanName, "registry"));
        String[] protocols = service.protocol();
        serviceConfig.setProtocols(
                DubboConfigUtil.parseProtocols(protocols, this.properties, environment, beanName, "registry"));
        String monitor = service.monitor();
        serviceConfig.setMonitor(
                DubboConfigUtil.parseMonitor(monitor, this.properties, environment, beanName, "monitor", monitor));
        String provider = service.provider();
        serviceConfig.setProvider(
                DubboConfigUtil.parseProvider(provider, this.properties, environment, beanName, "provider", provider));

        serviceConfig.setApplicationContext(this.applicationContext);
        serviceConfig.afterPropertiesSet();
        serviceConfig.setRef(bean);
        serviceConfig.export();
    }

}
