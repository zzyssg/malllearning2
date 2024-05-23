package com.zzy.mall.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
public class MallRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallRedisApplication.class, args);
    }

}
