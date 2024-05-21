package com.zzy.mall.tiny.config;

import com.zzy.mall.tiny.domain.AdminUserDetails;
import com.zzy.mall.tiny.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @ClassName MallSecurityConfig
 * @Author ZZy
 * @Date 2024/5/9 23:58
 * @Description
 * @Version 1.0
 */
@Configuration
public class MallSecurityConfig {

    @Autowired
    UmsAdminService umsAdminService;


    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            AdminUserDetails adminUserDetails = umsAdminService.getAdminByUsername(username);
            return adminUserDetails;
        };
    }


}
