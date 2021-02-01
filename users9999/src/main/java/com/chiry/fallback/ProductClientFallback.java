package com.chiry.fallback;

import com.chiry.clients.ProductClient;
import com.chiry.entiry.Product;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: first
 * @description: 使用feign 和 hystrix实现服务降级
 * @author: Chiry
 * @create: 2021-01-31 17:20
 **/
@Component
public class ProductClientFallback implements ProductClient{
    Map map=new HashMap<String ,Object>();
    @Override
    public String showMsg() {
        return "实现服务的降级！！user call product";
    }

    @Override
    public Map<String, Object> findOne(String productId) {
        map.put("status","false");
        map.put("msg","当前查询服务不可用！");
        return map;
    }

    @Override
    public Map<String, Object> update(Product product) {
        map.put("status","false11");
        map.put("msg","当前查询服务不可用！");
        return map;
    }
}
