package com.chiry.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
@Slf4j
public class CustomGlobalFilter implements GlobalFilter,Ordered {
  @Override
  public Mono<Void> filter (ServerWebExchange exchange, GatewayFilterChain chain){
    log.info("进入定义的filter");
    if(exchange.getRequest().getQueryParams().get("username")!=null){   //要求请求参数中必须包含username
      log.info("用户身份信息合法，放行请继续执行！！");
      return chain.filter(exchange);   //放行请求
    }
    log.info("非法用户，拒绝访问！！！");
    return exchange.getResponse().setComplete();  //拒绝请求，直接返回response
  }
  @Override
  public int getOrder(){   //filter 执行顺序。数字越小执行越早（-1在所有filter之前执行）
    return -1;    //执行最先
  }
}