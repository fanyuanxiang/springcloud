package com.chiry.controller;

import com.chiry.clients.ProductClient;
import com.chiry.entiry.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @program: first
 * @description:
 * @author: Chiry
 * @create: 2021-01-29 22:35
 **/
@Slf4j
@RestController
public class FeignController {
    @Autowired
    private ProductClient productClient;

    @GetMapping("/feign/test")
    public String test(){
        log.info("进入测试 feign 调用的方法");
        String msg=productClient.showMsg();
        log.info("调用商品服务返回的信息：{}",msg);
        return msg;
    }

    @GetMapping("/feign/test1")
    public Map<String ,Object> test2(String id){
        log.info("这测试使用feign的get方法传递参数进行服务间的通信");
        Map<String, Object> one = productClient.findOne(id);
        log.info("调用返回的信息：{}",one);
        return one;
    }


    @GetMapping("/feign/test3")
    public Map<String ,Object> test3(Product product){   //参数会自动封装上方法参数
        log.info("测试商品信息，进post 方式使用对象数据");
        Map<String, Object> update = productClient.update(product);
        log.info("调取product 服务后返回的参数。{}",update);
        return update;
    }
}
