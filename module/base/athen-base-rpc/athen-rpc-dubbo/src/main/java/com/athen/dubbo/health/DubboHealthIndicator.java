package com.athen.dubbo.health;

import com.alibaba.dubbo.rpc.service.EchoService;
import com.athen.dubbo.config.DubboConsumerAutoConfiguration;
import com.athen.dubbo.listener.ConsumerSubscribeListener;
import com.athen.dubbo.model.ClassIdBean;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;

/**
 * User: chenying
 * Date: 2019-07-30
 * Time: 15:52
 * since: 1.0.0
 */
public class DubboHealthIndicator extends AbstractHealthIndicator {
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        boolean up = true;
        for (ClassIdBean classIdBean : ConsumerSubscribeListener.SUBSCRIBEDINTERFACES_SET) {
            Object service = DubboConsumerAutoConfiguration.getDubboReference(classIdBean);
            EchoService echoService = (EchoService) service;
            if (echoService != null) {
                try {
                    echoService.$echo("Hello");
                    builder.withDetail(classIdBean.toString(), Status.UP.getCode());
                } catch (Throwable t) {
                    up = false;
                    builder.withDetail(classIdBean.toString(),
                            Status.DOWN.getCode() + ", message: " + t.getMessage());
                }
            }
        }
        if (up) {
            builder.up();
        } else {
            builder.down();
        }
    }
}
