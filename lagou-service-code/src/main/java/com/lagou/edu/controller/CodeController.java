package com.lagou.edu.controller;


import com.lagou.edu.pojo.LagouAuthCode;
import com.lagou.edu.service.AuthCodeService;
import com.lagou.edu.service.EmailFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
@RequestMapping("/code")
@RefreshScope
public class CodeController {



    @Autowired
    private EmailFeignClient emailFeignClient;

    @Autowired
    private AuthCodeService authCodeService;

    //验证码
    /**
     * ⽣生成验证码并发送到对应邮箱，成功true，失败false
     * @return
     */
    @SuppressWarnings("all")
    @RequestMapping("/create/{email}")
    public boolean create(@PathVariable("email") String email){
        String code = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
        //生成验证码
        if(emailFeignClient.SendEmail(email, code)) {
            return authCodeService.saveAuthCode(email,code);
        }else {
            //失败
            return false;
        }
    }

    /**
     * 校验验证码是否正确， 0正确1错误2超时
     */
    @RequestMapping("/validate/{email}/{code}")
    public Integer validate(@PathVariable("email") String email, @PathVariable("code") String code){
        LagouAuthCode authCode = authCodeService.getAuthCode(email, code);
        //错误
        if(authCode == null || !authCode.getCode().equals(code)){
            return 1;
        }else if( new Date().after(authCode.getExpiretime())){
            return 2;
        }
        return 0;
    }
}
