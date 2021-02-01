package com.chiry;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableHystrixDashboard
public class Hystrixdashboard9990Application {

	public static void main(String[] args) {
		SpringApplication.run(Hystrixdashboard9990Application.class, args);
	}
//	解决新版本springcloud 中hystrix dashboard无法使用的情况
//	新版中hystrix 不是默认映射 /hystrix.stream



}
