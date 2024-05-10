package com.zzy.mall.security.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MybatisConfig
 * @Author ZZy
 * @Date 2024/5/8 0:01
 * @Description
 * @Version 1.0
 */
@Configuration
@MapperScan(basePackages = {"com.zzy.mall.security.dao","com.zzy.mall.security.mbg.mapper"})
public class MybatisConfig {

}
