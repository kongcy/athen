package com.athen.system;

import com.athen.dubbo.annotation.EnableCustomDubboService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.athen.system.repository")
@EnableCustomDubboService
public class SysMain {
	
	public static void main(String args[]){
		SpringApplication.run(SysMain.class, args);
	}

}
