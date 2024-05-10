package com.zzy.mall.security.config;

import com.zzy.mall.security.component.JwtTokenFilter;
import com.zzy.mall.security.component.RestAccessDeniedHandler;
import com.zzy.mall.security.component.RestAuthenticationEntryPoint;
import com.zzy.mall.security.domain.AdminUserDetails;
import com.zzy.mall.security.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;

/**
 * @ClassName SecurityConfig
 * @Author ZZy
 * @Date 2024/5/9 22:35
 * @Description
 * @Version 1.0
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig {

    @Autowired
    JwtTokenFilter jwtTokenFilter;


    @Autowired
    RestAccessDeniedHandler restAccessDeniedHandler;

    @Autowired
    RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        //对filterChain设置
        httpSecurity.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,
                        "/",//授权访问静态资源
                        "/swagger-ui/index.html",
                        "/swagger-ui/**",
                        "/doc/**",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/swagger-resources/**",
                        "/v2/api-docs/**"
                ).permitAll()
                .antMatchers("/admin/login").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated();
        //禁用缓存
        httpSecurity.headers().cacheControl();
        //添加jwt过滤器
        httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //自定义错误返回信息
        httpSecurity.exceptionHandling().accessDeniedHandler(restAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint);
        return httpSecurity.build();


    }



}
