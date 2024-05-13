package com.zzy.mall.security.controller;

import com.zzy.mall.security.common.api.CommonResult;
import com.zzy.mall.security.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UmsMemberController
 * @Author ZZy
 * @Date 2024/5/13 22:35
 * @Description
 * @Version 1.0
 */
@RestController
@RequestMapping("/member")
@Api(tags = "UmsMemberController")
@Tag(name = "UmsMemberController", description = "会员管理")
public class UmsMemberController {

    @Autowired
    private UmsMemberService umsMemberService;

    @ApiOperation("生成验证码")
    @RequestMapping(value = "/generateAuthCode", method = RequestMethod.GET)
    public CommonResult generateAuthCode(String telNum) {

        String authCode = umsMemberService.generateAuthCode(telNum);
        return CommonResult.success(authCode);

    }


    @ApiOperation("校验验证码")
    @RequestMapping(value = "/validateAuthCode", method = RequestMethod.GET)
    public CommonResult validateAuthCode(@RequestParam("telNum") String telNum,
                                         @RequestParam("code") String code) {
        return CommonResult.success(umsMemberService.validateAuthCode(telNum, code));
    }
}
