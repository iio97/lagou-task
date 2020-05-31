package com.lagou.edu.service;

import com.lagou.edu.pojo.LagouToken;
import org.springframework.web.bind.annotation.PathVariable;

public interface ITokenService {

    /**
     * 注册接口
     */
    LagouToken register(String email, String password, String code);

    /**
     * 是否已注册，根据邮箱判断,true代表已经注册过， false代表尚未注册
     */
    boolean isRegistered(String email);

    /**
     * 登录接口，验证用户名密码合法性，根据用户名和密码生成token，
     * token存入数据库，并写入cookie中，登录成功返回邮箱地址，重定向到欢迎⻚页
     */
    LagouToken login( String email,String password);

    /**
     * 根据token查询用户登录邮箱接口
     * @param token
     * @return
     */
    String info(String token);


}
