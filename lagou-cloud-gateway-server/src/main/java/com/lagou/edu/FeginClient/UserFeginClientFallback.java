package com.lagou.edu.FeginClient;

import com.lagou.edu.pojo.LagouMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserFeginClientFallback implements UserFeginClient {


    @Override
    public LagouMessage info(String token) {

        return new LagouMessage();
    }
}
