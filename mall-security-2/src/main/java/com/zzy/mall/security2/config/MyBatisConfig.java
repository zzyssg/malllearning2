package com.zzy.mall.security2.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MyBatisConfig
 * @Author ZZy
 * @Date 2024/5/20 21:55
 * @Description
 * @Version 1.0
 */
@Configuration
@MapperScan(basePackages = {"com.zzy.mall.security2.dao","com.zzy.mall.security2.mbg.mapper"})
public class MyBatisConfig {
}
