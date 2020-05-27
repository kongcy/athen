package com.athen.dubbo.config;

import com.athen.dubbo.core.DubboServer;
import com.athen.dubbo.health.DubboHealthIndicator;
import com.athen.dubbo.model.DubboProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CountDownLatch;

/**
 * @author chenying
 * @data 2019-06-15 06:58
 */
@Configuration
@EnableConfigurationProperties(DubboProperties.class)
public class DubboAutoConfiguration {

    private boolean isConfig;

    /**
     * Start a non-daemon thread
     *
     * @return DubboServer
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "spring.dubbo", name = "server", havingValue = "true")
    public DubboServer dubboServer() {
        final DubboServer dubboServer = new DubboServer();
        final CountDownLatch latch = new CountDownLatch(1);
        Thread awaitThread = new Thread("dubboServer") {

            @Override
            public void run() {
                latch.countDown();
                dubboServer.await();
            }
        };
        awaitThread.setContextClassLoader(this.getClass().getClassLoader());
        awaitThread.setDaemon(false);
        awaitThread.start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }

        return dubboServer;
    }

    @Bean
    public DubboHealthIndicator dubboHealthIndicator() {
        return new DubboHealthIndicator();
    }

}
