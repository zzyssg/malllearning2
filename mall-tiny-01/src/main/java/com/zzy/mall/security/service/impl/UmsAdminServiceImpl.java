package com.zzy.mall.security.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.zzy.mall.security.common.util.JwtTokenUtil;
import com.zzy.mall.security.domain.AdminUserDetails;
import com.zzy.mall.security.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName UmsAdminServiceImpl
 * @Author ZZy
 * @Date 2024/5/8 23:27
 * @Description
 * @Version 1.0
 */
@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    private List<AdminUserDetails> adminUserDetailsList = new ArrayList<>();


    @PostConstruct

    private void init() {
        adminUserDetailsList.add(
                AdminUserDetails.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("123456"))
                        .authorities(CollUtil.toList("brand:list", "brand:listAll","brand:update","redis:all"))
                        .build()
        );
        adminUserDetailsList.add(
                AdminUserDetails.builder()
                        .username("zzy")
                        .password(passwordEncoder.encode("123456"))
                        .authorities(CollUtil.toList("brand:list","brand:update","redis:all","read:all"))
                        .build()
        );

    }

    //暂时使用UserDetails模拟从数据库查询，在类中注入UserDetails
    @Override
    public String login(String username, String password) {
        String token = null;
        //校验username是否存在
        AdminUserDetails adminUserDetails = getAdminByUsername(username);
        if (adminUserDetails == null) {
            return token;
        }
        //校验password是否正确
        if (!passwordEncoder.matches(password, adminUserDetails.getPassword())) {
            throw new UsernameNotFoundException("用户名或者密码错误");
        }
        //生成token
        token = jwtTokenUtil.generate(adminUserDetails);
        return token;

    }

    public AdminUserDetails getAdminByUsername(String username) {
        List<AdminUserDetails> adminList = adminUserDetailsList.stream().filter(item -> item.getUsername().equals(username)).collect(Collectors.toList());
        if (adminList != null) {
            return adminList.get(0);
        }
        return null;
    }


}
