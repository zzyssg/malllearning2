package com.zzy.mall.swagger.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName IgnoredUrlsConfig
 * @Author ZZy
 * @Date 2024/5/19 18:07
 * @Description
 * @Version 1.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "security.ignored")
public class IgnoredUrlsConfig {

    private List<String> urls = new ArrayList<>();


}
