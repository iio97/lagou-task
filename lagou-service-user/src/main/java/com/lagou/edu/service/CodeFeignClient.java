package com.lagou.edu.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="lagou-service-code",fallback = CodeFeignClientFallback.class,path = "/code")
public interface CodeFeignClient {


    @GetMapping("/validate/{email}/{code}")
    Integer validate(@PathVariable("email") String email, @PathVariable("code") String code);


}
