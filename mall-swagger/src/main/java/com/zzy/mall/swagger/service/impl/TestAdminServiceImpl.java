package com.zzy.mall.swagger.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzy.mall.swagger.common.util.JwtTokenUtil;
import com.zzy.mall.swagger.domain.AdminUserDetails;
import com.zzy.mall.swagger.dto.AdminLoginDto;
import com.zzy.mall.swagger.model.TestAdmin;
import com.zzy.mall.swagger.service.TestAdminService;
import com.zzy.mall.swagger.dao.TestAdminDao;
import jdk.nashorn.internal.ir.LiteralNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName TestAdminServiceImpl
 * @Author ZZy
 * @Date 2024/5/18 19:53
 * @Description
 * @Version 1.0
 */
@Service
public class TestAdminServiceImpl implements TestAdminService {

    @Autowired
    private TestAdminDao adminDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private List<AdminUserDetails> adminUserDetailsList = new ArrayList<>();


    @PostConstruct
    public void init() {

        adminUserDetailsList.add(AdminUserDetails.builder()
                .username("admin")
                .password(passwordEncoder.encode("123456"))
                .authorities(CollUtil.toList("brand:list", "brand:all"))
                .build());
        adminUserDetailsList.add(AdminUserDetails.builder()
                .username("zzy")
                .password(passwordEncoder.encode("123456"))
                .authorities(CollUtil.toList("brand:list"))
                .build()
        );

    }

    @Override
    public PageInfo<TestAdmin> list(Integer pageNum, Integer pageSize, String keyword) {

        PageHelper.startPage(pageNum, pageSize);
        List<TestAdmin> admins = adminDao.selectLikeUsername(keyword);
        PageInfo<TestAdmin> pageInfo = new PageInfo<>(admins);
        return pageInfo;
    }

    @Override
    public AdminUserDetails getAdminUserDetailsByUsername(String username) {
        List<AdminUserDetails> adminList = adminUserDetailsList.stream().filter(item -> item.getUsername().equals(username)).collect(Collectors.toList());
        return adminList.get(0);
    }

    @Override
    public String login(String username, String password) {
        //检查username是否存在
        AdminUserDetails userDetails = getAdminUserDetailsByUsername(username);
        if (userDetails == null) {
            throw new UsernameNotFoundException("用户名错误");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new UsernameNotFoundException("用户名或者密码错误");
        }
        //第一次登录时，设置上下文环境
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //检查password是否正确：password不能直接查询，因为库里存的也是加密后的，只能取出来和enCode的密码对比
        return jwtTokenUtil.generateToken(userDetails);
    }

}
