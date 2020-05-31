package com.lagou.edu.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CodeFeignClientFallback implements CodeFeignClient{


    @Override
    public Integer validate(String email, String code) {

        log.error("验证超时！！！");

        return -1;
    }
}
