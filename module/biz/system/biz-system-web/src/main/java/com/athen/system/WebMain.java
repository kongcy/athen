package com.athen.system;

import com.athen.dubbo.annotation.EnableCustomDubboService;
import com.athen.redis.annotation.EnableCustomRedisService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;

/**
 *
 */
@SpringBootApplication
@EnableSpringHttpSession
@EnableCustomRedisService
@EnableCustomDubboService
public class WebMain {
    public static void main(String[] args) {
        SpringApplication.run(WebMain.class, args);
    }
}
