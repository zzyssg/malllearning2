package com.zzy.mall.redis.config;

import com.zzy.mall.redis.component.JwtTokenFilter;
import com.zzy.mall.redis.component.RestAccessDeniedHandler;
import com.zzy.mall.redis.component.RestAuthenticationEntryPoint;
import com.zzy.mall.redis.domain.AdminUserDetails;
import com.zzy.mall.redis.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @ClassName SecurityConfig
 * @Author ZZy
 * @Date 2024/5/21 19:57
 * @Description
 * @Version 1.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {



    @Autowired
    private IgnoredUrlConfig ignoredUrlConfig;

    @Autowired
    RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    RestAccessDeniedHandler restAccessDeniedHandler;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UmsAdminService adminService;



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry urlRegistry = httpSecurity.authorizeRequests();
        //设置白名单
        for (String url : ignoredUrlConfig.getUrls()) {
            urlRegistry.antMatchers(url).permitAll();
        }
        //取消csrf-跨站请求攻击、设置无状态session、**、其余均需要验证
        httpSecurity.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS) //放行post请求前的OPTIONS请求
                .permitAll()
                .anyRequest()
                .authenticated();

        //取消缓存
        httpSecurity.headers().cacheControl();
        //添加jwt-token过滤器
        httpSecurity.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        //自定义异常处理
        httpSecurity.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
                .accessDeniedHandler(restAccessDeniedHandler);
        return httpSecurity.build();
    }

    @Bean
    UserDetailsService userDetailsService() {
        return username -> {
            AdminUserDetails adminUserDetails = adminService.getUserDetailsByName(username);
            return adminUserDetails;
        };

    }

    @Bean
    JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter();
    }

}
