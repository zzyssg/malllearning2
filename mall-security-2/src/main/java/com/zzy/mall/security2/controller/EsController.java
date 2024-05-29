package com.zzy.mall.security2.controller;


import com.zzy.mall.security2.common.api.CommonResult;
import com.zzy.mall.security2.nosql.EsProduct;
import com.zzy.mall.security2.service.EsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName EsController
 * @Author ZZy
 * @Date 2024/5/29 21:24
 * @Description Elasticsearch 商品管理
 * @Version 1.0
 */
@RestController
@RequestMapping("/es")
@Api(tags = "EsController")
@Tag(name = "EsController", description = "商品快速查询")
public class EsController {

    @Autowired
    EsProductService esProductService;

    @ApiOperation("导入数据")
    @RequestMapping(value = "/importAll", method = RequestMethod.GET)
    public CommonResult importAll() {
        esProductService.importAll();
        return CommonResult.success();
    }

    @ApiOperation("根据名称查询商品类")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public CommonResult search(@RequestParam("keywords") String keywords,
                               @RequestParam("pageNum") Integer pageNum,
                               @RequestParam("pageSize") Integer pageSize) {
        Page<EsProduct> productPage = esProductService.find(keywords, pageNum, pageSize);
        return CommonResult.success(productPage);
    }

    @ApiOperation("删除商品类")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public CommonResult delete(@RequestParam("id") Long id) {
        esProductService.delete(id);
        return CommonResult.success();
    }

    @ApiOperation("创建商品搜索")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public CommonResult add(@RequestBody EsProduct esProduct) {
        int count = esProductService.create(esProduct);
        if (count > 0) {
            return CommonResult.success();
        } else {
            return CommonResult.failed("创建搜索失败！");
        }
    }

}
