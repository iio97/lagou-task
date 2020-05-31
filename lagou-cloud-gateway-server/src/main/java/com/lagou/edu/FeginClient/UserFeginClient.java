package com.lagou.edu.FeginClient;


import com.lagou.edu.pojo.LagouMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="lagou-service-user",fallback = UserFeginClientFallback.class,path = "/user")
public interface UserFeginClient {

    @GetMapping("/info/{token}")
    LagouMessage info(@PathVariable("token") String token);

}
