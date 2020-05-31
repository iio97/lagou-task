package com.lagou.edu;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication  //boot容器
@EnableEurekaServer     //声明为Eeuka服务器
public class EuerkaServerApplication8761 {

    public static void main(String[] args) {

        //Euerka服务端启动
        SpringApplication.run(EuerkaServerApplication8761.class,args);

    }

}
