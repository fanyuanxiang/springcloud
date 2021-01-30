package com.chiry.controller;

import com.sun.deploy.util.SessionState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.xml.transform.Source;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @program: first
 * @description:  测试不同服务调用通过RestTemplate类基于restful风格调用其他微服务的controller
 * @author: Chiry
 * @create: 2021-01-27 14:45
 **/
@RestController
@Slf4j
public class UserController {
//    1。第一种调用方式， 服务地址 ：http://localhost:9998/product/showMsg

    //使用discoveryClient获取注册中心的实例对象
    @Autowired
    private DiscoveryClient discoveryClient;
    //使用LoadBalancerClient获取实现了负载均衡过后的服务实例
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    //restTemplate 使用获取其他服务的http请求
    RestTemplate restTemplate=new RestTemplate();
//        直接使用RestTemplate 方式调用其他服务
    @GetMapping("/user/showMsg")
    public String showProductMsg(){
        String msg=restTemplate.getForObject("http://"+randomHost()+"/product/showMsg",String.class);
        log.info("调用成功信息：{}",msg);
        return msg;
    }

//    使用Ribbon方式通过注册中心调用服务
    @GetMapping("/user/showMsgRb")
    public List<ServiceInstance> showProductMsgRb(){
        List<ServiceInstance> products = discoveryClient.getInstances("products");
        for (ServiceInstance instance :products){
            log.info("服务主机:{}",instance.getHost());
            log.info("服务端口:{}",instance.getPort());
            log.info("服务地址:{}",instance.getUri());
        }
        //2。使用loadBalanceclient 实现负载均衡之后获取到的一个实例服务对象
        ServiceInstance instance = loadBalancerClient.choose("products");
        log.info("uri:{}",instance.getUri());
        // 在Restful 中会自动将对象转换成json格式数据
        String product = restTemplate.getForObject(instance.getUri() + "/product/showMsg", String.class);
        System.out.println("获取到product服务："+product);
        return products;
    }


    //随机取主机地址端口
    public static String randomHost(){
        List<String> list=new ArrayList<>();
        list.add("localhost:9998");
        list.add("localhost:9997");
        int i = new Random().nextInt(2);
        return list.get(i);
    }
}
