package com.athen.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * User: chenying
 * Date: 2019-08-13
 * Time: 17:02
 * since: 1.0.0
 */
@SpringBootApplication
@EnableConfigServer
@MapperScan(basePackages = "com.athen.config.repository")
public class Main {
    public static void main(String [] args){
        SpringApplication.run(Main.class,args);
    }
}
