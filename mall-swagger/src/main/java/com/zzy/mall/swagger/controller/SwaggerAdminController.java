package com.zzy.mall.swagger.controller;

import com.zzy.mall.swagger.common.api.CommonResult;
import com.zzy.mall.swagger.common.util.JwtTokenUtil;
import com.zzy.mall.swagger.dto.AdminLoginDto;
import com.zzy.mall.swagger.service.TestAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SwaggerAdminController
 * @Author ZZy
 * @Date 2024/5/19 15:37
 * @Description
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping("/admin")
@Api(tags = "SwaggerAdminController")
@Tag(name = "SwaggerAdminController", description = "管理后台")
public class SwaggerAdminController {

    @Autowired
    private TestAdminService adminService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation("后台登录")
    public CommonResult login(@RequestBody AdminLoginDto adminLoginDto) {
        //通用返回类、通用状态码、登录dto
        String token = adminService.login(adminLoginDto.getUsername(), adminLoginDto.getPassword());
        log.info("logging user:{}", adminLoginDto);
        log.info("logged token:{}",token);
        return CommonResult.success(token);
    }
}
