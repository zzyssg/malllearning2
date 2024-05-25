package com.zzy.mall.redis.controller;

import com.zzy.mall.redis.common.api.CommonResult;
import com.zzy.mall.redis.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UmsMemberController
 * @Author ZZy
 * @Date 2024/5/24 12:34
 * @Description
 * @Version 1.0
 */
@RestController
@RequestMapping("/sso")
@Api(tags = "UmsMemberController")
@Tag(name = "UmsMemberController",description = "会员管理")
public class UmsMemberController {

    @Autowired
    UmsMemberService memberService;

    @ApiOperation("发送验证码")
    @RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('brand:list')")
    public CommonResult getAuthCode(@RequestParam String telNum) {
        return memberService.getAuthCode(telNum);

    }

    @ApiOperation("发送验证码")
    @RequestMapping(value = "/verifyCode", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('brand:list')")
    public CommonResult verifyCode(@RequestParam String telNum, @RequestParam String code) {
        return memberService.verifyCode(telNum, code);
    }


}
