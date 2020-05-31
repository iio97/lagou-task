package com.lagou.edu.service;

import com.lagou.edu.pojo.LagouAuthCode;

public interface AuthCodeService {

    /**
     * 保存验证码
     * @return
     */
    boolean saveAuthCode(String email,String code);

    /*
        校验验证码是否正确
     */
    LagouAuthCode getAuthCode(String email,String code);

}
