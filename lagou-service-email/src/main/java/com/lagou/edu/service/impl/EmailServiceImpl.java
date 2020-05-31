package com.lagou.edu.service.impl;

import com.lagou.edu.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {


    @Autowired
    JavaMailSender mailSender;//spring提供的发送类


    @Override
    public boolean sendEmail(String email,String code) {

        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom("zhulongqiu123@163.com");//发送者
        mailMessage.setTo(email);//接收者
        mailMessage.setSubject("注册验证码");//标题
        mailMessage.setText(code);//内容
        try {
            mailSender.send(mailMessage);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }
    }
}
