package com.lagou.edu;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EntityScan("com.lagou.edu.pojo")   //扫描实体类
@EnableDiscoveryClient              //Euerka客户端通用版本
@EnableFeignClients                 //Feign功能引入
public class UserApplication {


    public static void main(String[] args) {

        SpringApplication.run(UserApplication.class,args);

    }


}
