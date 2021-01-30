package com.chiry.controller;

import com.netflix.loadbalancer.IRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @program: first
 * @description:  使用@LoadBalance
 * @author: Chiry
 * @create: 2021-01-27 21:41
 **/
@RestController
public class UserController2 {
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/user/Msg")
    public String UserController(){
        IRule  irule;
        //直接使用微服id名获取负载均衡后的服务实例对象节点实例instance
        String forObject = restTemplate.getForObject("http://products/product/showMsg", String.class);
        return "这是测试@RestTemplate 实现负载均衡的请求:"+forObject;
    }
}
