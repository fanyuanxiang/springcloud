package com.chiry.controller;

import com.chiry.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
        return "展示商品信息" + port;
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
}
