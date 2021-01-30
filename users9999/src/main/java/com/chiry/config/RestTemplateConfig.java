package com.chiry.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @program: first
 * @description:使用 @LoadBalance
 * @author: Chiry
 * @create: 2021-01-27 21:38
 **/
@Configuration          //指定这是一个配置配，并交给spring工厂
public class RestTemplateConfig {

    @Bean   //把对象交给spring 工厂管理
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
