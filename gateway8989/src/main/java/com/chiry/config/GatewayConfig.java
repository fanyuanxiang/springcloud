package com.chiry.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: first
 * @description: 使用java类配置路由转发
 * @author: Chiry
 * @create: 2021-02-01 22:48
 **/
@Configuration
public class GatewayConfig {
        @Bean
        public RouteLocator coustomRouteLocator(RouteLocatorBuilder builder){
            return builder.routes()
                    .route("order_route",r->r.path("/user/**").uri("http://localhost:9999")).build();
        }
        //表示该网关路径转发http://localhost:port/order/**--->http://localhost:9997/order/**
}
