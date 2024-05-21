package com.zzy.mall.security.controller;

import com.zzy.mall.security.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName PmsBrandController
 * @Author ZZy
 * @Date 2024/5/21 23:13
 * @Description
 * @Version 1.0
 */
@RestController
@RequestMapping("/brand")
@Api(tags = "PmsBrandController")
@Tag(name = "PmsBrandController", description = "品牌管理")
public class PmsBrandController {

    @ApiOperation("查询品牌")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('brand:list')")
    public CommonResult list() {
        return CommonResult.success("test list");
    }

    @ApiOperation("查询全部品牌")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('brand:listAll')")
    public CommonResult listAll() {
        return CommonResult.success("test listAll");
    }
}
