package com.lagou.edu.service;

public interface EmailService {

    /**
     * 发送验证码到邮箱， true成功， false失败
     */
    boolean sendEmail(String email,String code);

}
