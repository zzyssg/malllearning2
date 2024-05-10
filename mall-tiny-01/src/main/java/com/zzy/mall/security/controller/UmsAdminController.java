package com.zzy.mall.security.controller;

import com.zzy.mall.security.common.api.CommonResult;
import com.zzy.mall.security.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UmsAdminController
 * @Author ZZy
 * @Date 2024/5/8 23:10
 * @Description
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "UmsAdminController")
@Tag(name = "UmsAdminController",description = "后台管理")
public class UmsAdminController {


    @Autowired
    UmsAdminService umsAdminService;

    @ApiOperation("登录后台")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public CommonResult login(@RequestParam("username")String username, @RequestParam("password")String password) {
        String token = umsAdminService.login(username, password);

        return CommonResult.success(token);
    }
}
