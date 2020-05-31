package com.lagou.edu.controller;


import com.lagou.edu.pojo.LagouMessage;
import com.lagou.edu.pojo.LagouToken;
import com.lagou.edu.pojo.StatusCode;
import com.lagou.edu.service.CodeFeignClient;
import com.lagou.edu.service.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 关于用户登录等的微服务应用
 */
@RestController
@RequestMapping("/user")
@RefreshScope //刷新git配置
public class UserController {



    @Autowired
    private ITokenService iTokenService;

    @Autowired
    private CodeFeignClient codeFeignClient;

    /**
     * REST请求风格
     * @PathVariable 映射 URL 绑定的占位符
     * @param email     邮箱
     * @param password  密码
     * @param code      验证码
     * @return 注册接口， true成功， false失败
     */
    @RequestMapping("/register/{email}/{password}/{code}")
    public LagouMessage register(@PathVariable("email") String email,
                                 @PathVariable("password") String password,
                                 @PathVariable("code") String code,
                                 HttpServletRequest request, HttpServletResponse response){


        if(iTokenService.isRegistered(email)){
            //已经注册
            return new LagouMessage(StatusCode.ERROR.value(), "账号已注册");
        }
        Integer validate = codeFeignClient.validate(email, code);
        if(validate == 1){
            //错误
            return new LagouMessage(StatusCode.ERROR.value(), "验证码错误");
        }
        if(validate == 2){
            //错误
            return new LagouMessage(StatusCode.ERROR.value(), "验证码超时");
        }

        //注册
        LagouToken lagouToken = iTokenService.register(email, password, code);


        Cookie cookie = new Cookie("token", lagouToken.getToken());
        cookie.setPath("/");
        response.addCookie(cookie);

        return new LagouMessage(StatusCode.CORRECT.value(), "注册成功");
    }

    /**
     * @param email 邮箱
     * @return 是否已注册，根据邮箱判断,true代表已经注册过， false代表尚未注册
     */
    @RequestMapping("/isRegistered/{email}")
    public boolean isRegistered(@PathVariable("email") String email){

        return iTokenService.isRegistered(email);
    }

    /**
     * 登录接口，验证用户名密码合法性，根据
     * ⽤用户名和密码生成token， token存入数
     * 据库，并写入cookie中，登录成功返回邮
     * 箱地址，重定向到欢迎⻚页
     * @param email
     * @param password
     * @return
     */
    @RequestMapping("/login/{email}/{password}")
    public LagouMessage login(@PathVariable("email") String email,
                              @PathVariable("password") String password,
                              HttpServletRequest request, HttpServletResponse response){

        LagouToken lagouToken = iTokenService.login(email, password);

        //登录失败
        if(lagouToken == null){
            return new LagouMessage(StatusCode.ERROR.value(), "用户名或密码错误");
        }

        Cookie cookie = new Cookie("token", lagouToken.getToken());
        cookie.setPath("/");
        response.addCookie(cookie);

        return new LagouMessage(StatusCode.CORRECT.value(), "登录成功");
    }

    /**
     * 根据token查询⽤用户登录邮箱接口
     * @param token
     * @return 邮箱地址
     */
    @RequestMapping("/info/{token}")
    public LagouMessage info(@PathVariable("token") String token){

        if(iTokenService.info(token) != null){
            return new LagouMessage(StatusCode.CORRECT.value(), StatusCode.CORRECT.desc(), iTokenService.info(token));
        }else {
            LagouMessage lagouMessage = new LagouMessage(StatusCode.ERROR.value(), StatusCode.ERROR.desc(), "not token!");
            return lagouMessage;
        }
    }


}
