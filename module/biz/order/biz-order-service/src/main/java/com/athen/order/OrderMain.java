package com.athen.order;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
@MapperScan(basePackages = "com.athen.order.repository")
public class OrderMain {
	
	public static void main(String args[]){
		SpringApplication.run(OrderMain.class, args);
	}

}
