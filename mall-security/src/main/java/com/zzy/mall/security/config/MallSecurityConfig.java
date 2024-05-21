package com.zzy.mall.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
  *@ClassName MallSecurityConfig
  *@Author ZZy
  *@Date 2024/5/21 20:58
  *@Description
  *@Version 1.0
*/
@Configuration
public class MallSecurityConfig {


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
