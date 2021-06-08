package com.chiry.controller;

import com.chiry.entity.Product;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.utils.FallbackMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: first
 * @description: test controller call  ,服务之间所有都使用restcontroller  方便微服务之间的相互调用。
 * @author: Chiry
 * @create: 2021-01-27 10:35
 **/
@RestController
@Slf4j
public class ProductController {
    @Value("${server.port}")
    private int port;

    @GetMapping("/product/showMsg")
    public String showMsg(){
        log.info("展示商品信息......");
        return "展示商品信息 " + port;
    }

    @GetMapping("/product/findOne")
    public Map<String,Object> findOne(String productId) {
        Map map =new HashMap<String,Object>();
        map.put("status",true);
        map.put("msg","根据商品id查询商品信息成功！端口号："+port);
        map.put("productId",productId);
        return map;
    }
    @PostMapping("/product/upate")
    //使用@RequestBody注解标记参数，把http通信的json格式转换成对象信息。
    public Map<String ,Object > update(@RequestBody Product product){
        Map map=new HashMap<String ,Object>();
        map.put("status",true);
        map.put("msg","更新product信息");
        map.put("product:",product);
        return map;
    }
    @RequestMapping("/product/break")
    @HystrixCommand(fallbackMethod = "fallbackmethod")
    public String testBreak(Integer  id ){
        if(id<0){
            throw new RuntimeException("参数不合法id="+id+"服务端口为"+port);
        }
        return "请求成功当前参数为+"+id+"服务端口为"+port;
    }
    public String fallbackmethod(Integer id ){   //这是一个熔断触发的方法 ，参数最好和声名者一致，好接收参数
        return "参数不合法"+id+"触发熔断机制";
    }

}
