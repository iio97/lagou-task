package com.lagou.edu.controller;

import com.lagou.edu.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/email")
@RefreshScope
public class EmaliController {


        @Autowired
        private EmailService emailService;

        /**
         * @return 发送验证码到邮箱， true成功， false失败
         */
        @RequestMapping("/{email}/{code}")
        public boolean SendEmail(@PathVariable("email") String email,
                                      @PathVariable("code") String code){

                return emailService.sendEmail(email,code);
        }



}
