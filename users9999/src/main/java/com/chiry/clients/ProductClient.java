package com.chiry.clients;

import com.chiry.entiry.Product;
import com.chiry.fallback.ProductClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


//调用商品服务的Openfeign 组件
@FeignClient(name = "products" ,fallback = ProductClientFallback.class)   //作用：用来标识当前接口是一个feign的组件， value\name（default）：书写的是你要调用服务id
public interface ProductClient {    //该接口类直接使用，系统会自动进行实现。

    @GetMapping("/product/showMsg")
    String showMsg();    //表示我们要调用的其他微服务的接口。


    @GetMapping("/product/findOne")  //注意使用OpenFeign 的GET方式传递参数，参数变量必须使用@RequestParam注解进行修饰
    //如果不使用该参数则会出现error： feign.FeignException$MethodNotAllowed :[405]   、typw:internal Server Error ,status=500
    public Map<String,Object> findOne(@RequestParam("productId") String productId);
    @PostMapping("/product/upate")
    //使用@RequestBody注解标记参数，把http通信的json格式转换成对象信息。
    public Map<String ,Object > update(@RequestBody Product product);
}