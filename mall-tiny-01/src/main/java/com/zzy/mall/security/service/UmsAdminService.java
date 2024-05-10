package com.zzy.mall.security.service;

import com.zzy.mall.security.domain.AdminUserDetails;

/**
 * @ClassName UmsAdminService
 * @Author ZZy
 * @Date 2024/5/8 23:27
 * @Description
 * @Version 1.0
 */
public interface UmsAdminService {

    String login(String username, String password);


    AdminUserDetails getAdminByUsername(String username);
}
