package com.zzy.mall.swagger.config;

import com.zzy.mall.swagger.domain.AdminUserDetails;
import com.zzy.mall.swagger.service.TestAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @ClassName MallSecutiryConfig
 * @Author ZZy
 * @Date 2024/5/19 21:28
 * @Description
 * @Version 1.0
 */
@Configuration
public class MallSecurityConfig {

    @Autowired
    private TestAdminService adminService;
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            AdminUserDetails adminUserDetails = adminService.getAdminUserDetailsByUsername(username);
            if (adminUserDetails != null) {
                return adminUserDetails;
            }
            throw new UsernameNotFoundException("用户名或者密码输入错误");

        };
    }

}
