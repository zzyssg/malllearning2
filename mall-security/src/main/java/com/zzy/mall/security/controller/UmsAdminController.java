package com.zzy.mall.security.controller;

import com.zzy.mall.security.common.api.CommonResult;
import com.zzy.mall.security.dto.AdminLoginDto;
import com.zzy.mall.security.mbg.model.UmsAdmin;
import com.zzy.mall.security.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UmsAdminController
 * @Author ZZy
 * @Date 2024/5/20 23:25
 * @Description
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "UmsAdminController")
@Tag(name = "UmsAdminController", description = "管理员后台")
public class UmsAdminController {

    @Autowired
    UmsAdminService adminService;

    @ApiOperation("根据ID查询用户")
    @RequestMapping(value = "/search/{id}",method = RequestMethod.GET)
    public CommonResult search(@PathVariable("id") Long id) {
        UmsAdmin umsAdmin = adminService.selectById(id);
        return CommonResult.success(umsAdmin);
    }

    @ApiOperation("查询用户token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult login(@RequestBody AdminLoginDto adminLoginDto) {
        String token = adminService.login(adminLoginDto.getUsername(), adminLoginDto.getPassword());
        if (token != null) {
            return CommonResult.success(token);
        } else {
            return CommonResult.failed("用户名或者密码错误");
        }
    }
}
