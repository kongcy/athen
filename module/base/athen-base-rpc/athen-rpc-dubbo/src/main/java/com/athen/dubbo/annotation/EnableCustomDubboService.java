package com.athen.dubbo.annotation;

import com.athen.dubbo.config.DubboAutoConfiguration;
import com.athen.dubbo.config.DubboConsumerAutoConfiguration;
import com.athen.dubbo.config.DubboProviderAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author chenying
 * @date 2019-06-16 17:34
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
@Import({DubboAutoConfiguration.class,
        DubboProviderAutoConfiguration.class,
        DubboConsumerAutoConfiguration.class})
public @interface EnableCustomDubboService {

}
