package com.zzy.mall.security.controller;

import com.zzy.mall.security.common.api.CommonResult;
import com.zzy.mall.security.mbg.model.PmsBrand;
import com.zzy.mall.security.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.Cacheable;

import java.util.List;

/**
 * @ClassName PmsBrandController
 * @Author ZZy
 * @Date 2024/5/9 0:14
 * @Description
 * @Version 1.0
 */
@RestController
@RequestMapping("/brand")
@Api(tags = "PmsBrandController")
@Tag(name = "PmsBrandController", description = "品牌管理")
public class PmsBrandController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);

    @Autowired
    private PmsBrandService pmsBrandService;

    @ApiOperation("查询全部品牌")
    @PreAuthorize("hasAuthority('brand:listAll')")
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    public CommonResult listAll() {
        return CommonResult.success("listAll");
    }

    @ApiOperation("查询品牌")
    @PreAuthorize("hasAuthority('brand:list')")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult list(@RequestParam("pageNum") Integer pageNum,
                             @RequestParam("pageSize") Integer pageSize) {
        List<PmsBrand> list = pmsBrandService.list(pageNum, pageSize);
        return CommonResult.success(list);
    }

    @ApiOperation("根据ID查询物品")
    @PreAuthorize("hasAuthority('brand:list')")
    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    public CommonResult getItem(@PathVariable Long id) {
        PmsBrand brand = pmsBrandService.getItem(id);
        return CommonResult.success(brand);
    }

    @ApiOperation("更新Brand")
    @PreAuthorize("hasAuthority('brand:update')")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult updateItem(@PathVariable Long id, @RequestBody PmsBrand pmsBrandDto) {
        CommonResult commonResult;
        int count = pmsBrandService.update(id, pmsBrandDto);
        if (count > 0) {
             commonResult= CommonResult.success(count);
            LOGGER.info("update brand：{}", pmsBrandDto);
        } else {
            commonResult = CommonResult.failed();
            LOGGER.debug("update brand failed：{}", pmsBrandDto);
        }
        return commonResult;
    }

}
