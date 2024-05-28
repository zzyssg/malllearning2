package com.zzy.mall.rabbitmq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @ClassName GlobalCorsConfig
 * @Author ZZy
 * @Date 2024/5/28 21:54
 * @Description 全局跨域配置
 * @Version 1.0
 */
@Configuration
public class GlobalCorsConfig {

    //允许跨域调用的过滤器
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //允许所有域名跨域调用 TODO 这里是否可以在配置文件里设置白名单
        corsConfiguration.addAllowedOriginPattern("*");
        //允许发送cookie
        corsConfiguration.setAllowCredentials(true);
        //放行全部原始头信息
        corsConfiguration.addAllowedHeader("*");
        //允许所有请求方法跨域调用
        corsConfiguration.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }




}
