package com.lagou.edu.service;


import com.lagou.edu.pojo.LagouMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailFeignFallBack implements EmailFeignClient{


    @Override
    public boolean SendEmail(String email, String code) {

        log.debug("发送邮件超时！！！！");

        return false;
    }
}
