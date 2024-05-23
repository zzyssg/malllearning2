package com.zzy.mall.redis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName IgnoredUrlConfig
 * @Author ZZy
 * @Date 2024/5/21 19:57
 * @Description
 * @Version 1.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "secure.ignored")
public class IgnoredUrlConfig {

    List<String> urls = new ArrayList<>();

}
