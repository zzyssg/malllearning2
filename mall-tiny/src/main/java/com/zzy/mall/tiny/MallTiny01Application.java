package com.zzy.mall.tiny;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class MallTiny01Application {

    public static void main(String[] args) {
        SpringApplication.run(MallTiny01Application.class, args);
    }

}
