package com.chiry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient   //可省略
@EnableFeignClients     //这是使用OpenFeign的声名注解，开启支持OpenFeign
public class Users9999Application {

	public static void main(String[] args) {
		SpringApplication.run(Users9999Application.class, args);
	}

}
