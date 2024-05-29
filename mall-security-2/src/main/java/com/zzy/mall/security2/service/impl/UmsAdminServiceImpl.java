package com.zzy.mall.security2.service.impl;


import cn.hutool.core.collection.CollUtil;
import com.zzy.mall.security2.common.util.JwtTokenUtil;
import com.zzy.mall.security2.domain.AdminUserDetails;
import com.zzy.mall.security2.mbg.mapper.UmsAdminMapper;
import com.zzy.mall.security2.mbg.model.UmsAdmin;
import com.zzy.mall.security2.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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
 * @Date 2024/5/20 22:43
 * @Description
 * @Version 1.0
 */
@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    private List<AdminUserDetails> adminUserDetailsList = new ArrayList<>();
    @Autowired
    private UmsAdminMapper adminMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenUtil jwtTokenUtil;


    @PostConstruct
    void postConstruct() {
        adminUserDetailsList.add(AdminUserDetails.builder()
                .username("admin")
                .password(passwordEncoder.encode("123456"))
                .authorities(CollUtil.toList("brand:list", "brand:add"))
                .build()
        );
        adminUserDetailsList.add(AdminUserDetails.builder()
                .username("zzy")
                .password(passwordEncoder.encode("123456"))
                .authorities(CollUtil.toList("brand:list"))
                .build()
        );
    }

    @Override
    public UmsAdmin selectById(Long id) {
        return adminMapper.selectByPrimaryKey(id);
    }


    @Override
    public String login(String username, String password) {
        String token = null;
        //判断用户名
        AdminUserDetails userDetailsByName = getUserDetailsByName(username);
        if (userDetailsByName == null) {
            return token;
        }
        //判断密码是否正确
        if (!passwordEncoder.matches(password, userDetailsByName.getPassword())) {
            throw new UsernameNotFoundException("用户名或者密码错误");
        }
        SecurityContextHolder.getContext().setAuthentication(UsernamePasswordAuthenticationToken.authenticated(userDetailsByName, null, userDetailsByName.getAuthorities()));
        //调用jtwUtil生成token
        return jwtTokenUtil.generateToken(userDetailsByName);
    }

    @Override
    public AdminUserDetails getUserDetailsByName(String username) {
        //模拟从数据库中根据username查询用户信息
        List<AdminUserDetails> adminUserDetails = adminUserDetailsList.stream().filter(admin -> username.equals(admin.getUsername())).collect(Collectors.toList());
        if (!adminUserDetails.isEmpty()) {
            return adminUserDetails.get(0);
        }
        return null;
    }
}
