package com.lagou.edu.filter;

import com.lagou.edu.FeginClient.UserFeginClient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@Slf4j
public class GateWatFilter implements GlobalFilter, Ordered {



    @Value("${lagou.times}")
    private int times;
    @Value("${lagou.second}")
    private int second;

    Map<String,RequestStrategy> explosionMap = new HashMap<>();
    private static List<String> blackList = new ArrayList<>();



    @Autowired
    private UserFeginClient userFeginClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //开始时间
        long startTime = System.currentTimeMillis();

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        String clientIp = request.getRemoteAddress().getHostString();
        String requestPath = request.getPath().toString();


        //防爆
        if(requestPath.indexOf("/user/register/") != -1) {
            if (explosionMap.get(clientIp) == null){
                RequestStrategy requestStrategy = new RequestStrategy();
                requestStrategy.setCount(1);
                requestStrategy.setStarttime(startTime);
                requestStrategy.setEndtime(System.currentTimeMillis());
                explosionMap.put(clientIp,requestStrategy);
            }else {
                RequestStrategy requestStrategy = explosionMap.get(clientIp);
                int count = requestStrategy.getCount() + 1;
                requestStrategy.setCount(count);
                requestStrategy.setEndtime(System.currentTimeMillis());
                explosionMap.put(clientIp,requestStrategy);
            }

            explosionMap.forEach((key,requestStrategy)->{
                if(requestStrategy.getEndtime() - requestStrategy.getStarttime() < (times*60000) && requestStrategy.getCount() >= second ){
                    System.out.println("在" + times/60000  + "分钟请求次数达到: " +requestStrategy.getCount());
                    blackList.add(key);
                }
            });

            boolean flag = false;

            for (int i = 0; i < blackList.size(); i++) {
                if (blackList.get(i).equals(clientIp)){
                    flag = true;
                    break;
                }
            }
            if(flag){
                //黑名单
                System.out.println("IP:" + clientIp  + "处于黑名单,无法访问 ");
                String data = "IP:" + clientIp  + "处于黑名单,无法访问 ";
                DataBuffer wrap = response.bufferFactory().wrap(data.getBytes());
                return response.writeWith(Mono.just(wrap));
            }
        }


        //合法请求
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RequestStrategy {

        private long starttime;//IP最先请求时间
        private long endtime;  //IP最后请求时间
        private int count;    //请求次数

    }
}



