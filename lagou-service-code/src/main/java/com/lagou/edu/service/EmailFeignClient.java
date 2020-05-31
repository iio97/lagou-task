package com.lagou.edu.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="lagou-service-email",fallback = EmailFeignFallBack.class,path = "/email")
public interface EmailFeignClient {

    /*
        Feign远程调用
     */
    @GetMapping(value = "/{email}/{code}")
    boolean SendEmail(@PathVariable("email") String email, @PathVariable("code") String code);


}
