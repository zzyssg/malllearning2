package com.zzy.mall.swagger.service;

import com.github.pagehelper.PageInfo;
import com.zzy.mall.swagger.domain.AdminUserDetails;
import com.zzy.mall.swagger.dto.AdminLoginDto;
import com.zzy.mall.swagger.model.TestAdmin;

/**
 * @ClassName TestAdminService
 * @Author ZZy
 * @Date 2024/5/18 19:52
 * @Description
 * @Version 1.0
 */
public interface TestAdminService {

    PageInfo<TestAdmin> list(Integer pageNum, Integer pageSize, String keyword);


    AdminUserDetails getAdminUserDetailsByUsername(String username);


    String login(String username, String password);
}
