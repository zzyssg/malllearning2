package com.zzy.mall.test.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MybatisConfig
 * @Author ZZy
 * @Date 2024/5/16 20:56
 * @Description
 * @Version 1.0
 */
@Configuration
@MapperScan(basePackages = {"com.zzy.mall.test.dao"})
public class MybatisConfig {
}
