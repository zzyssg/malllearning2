package com.zzy.mall.security2.service;


import com.zzy.mall.security2.domain.AdminUserDetails;
import com.zzy.mall.security2.mbg.model.UmsAdmin;

/**
 * @ClassName UmsAdminService
 * @Author ZZy
 * @Date 2024/5/20 22:43
 * @Description
 * @Version 1.0
 */
public interface UmsAdminService {

    UmsAdmin selectById(Long id);


    String login(String username, String password);

    AdminUserDetails getUserDetailsByName(String username);


}
