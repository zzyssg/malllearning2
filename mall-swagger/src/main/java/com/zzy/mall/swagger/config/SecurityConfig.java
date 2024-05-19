package com.zzy.mall.swagger.config;

import com.zzy.mall.swagger.component.JwtAuthenticationFilter;
import com.zzy.mall.swagger.component.RestAccessDeniedHandler;
import com.zzy.mall.swagger.component.RestAuthenticationEntryPoint;
import com.zzy.mall.swagger.domain.AdminUserDetails;
import com.zzy.mall.swagger.service.TestAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;

/**
 * @ClassName SecurityConfig
 * @Author ZZy
 * @Date 2024/5/19 17:51
 * @Description
 * @Version 1.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig {

    @Autowired
    private RestAccessDeniedHandler restAccessDeniedHandler;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    IgnoredUrlsConfig ignoredUrlsConfig;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry urlRegistry = httpSecurity.authorizeRequests();
/*        HttpSecurity security = httpSecurity.csrf().disable()//禁用csrf-跨站请求攻击，jwt完成认证，不存在csrf问题
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();*/
        //对白名单放行
        for (String url : ignoredUrlsConfig.getUrls()) {
            urlRegistry.antMatchers(url).permitAll();
            System.out.println(url);
        }

        //对OPTION请求放行
        urlRegistry.antMatchers(HttpMethod.OPTIONS)
                .permitAll();
        //禁用csrf-jwt认证，没有这种风险、禁用session管理
        httpSecurity.csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated();

        //请求头禁用缓存
        httpSecurity.headers().cacheControl();

        //添加jwt过滤器
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        //添加自定义未授权和登录结果返回页
        httpSecurity.exceptionHandling()
                .accessDeniedHandler(restAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint);

        return httpSecurity.build();

    }

    private JwtAuthenticationFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationFilter();
    }




    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
