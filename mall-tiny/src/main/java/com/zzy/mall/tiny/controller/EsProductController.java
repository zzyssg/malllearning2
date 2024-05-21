package com.zzy.mall.tiny.controller;

import com.zzy.mall.tiny.common.api.CommonResult;
import com.zzy.mall.tiny.nosql.elasticsearch.document.EsProduct;
import com.zzy.mall.tiny.service.EsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ElasticsearchController
 * @Author ZZy
 * @Date 2024/5/14 22:54
 * @Description
 * @Version 1.0
 */
@RestController
@RequestMapping("/elastic")
@Api(tags = "EsProductController")
@Tag(name = "EsProductController", description = "商品快速搜索")
public class EsProductController {

    @Autowired
    EsProductService esProductService;

    @ApiOperation("导入数据到Elasticsearch")
    @RequestMapping(value = "/importAll", method = RequestMethod.GET)
    public CommonResult importAll() {
        int count = esProductService.importAll();
        return CommonResult.success(count);
    }

    @ApiOperation("商品快速分页查询")
    @RequestMapping(value = "/simpleSearch", method = RequestMethod.POST)
    public CommonResult simpleSearch(@RequestParam("keywords") String keywords,
                                     @RequestParam("pageNum") Integer pageNum,
                                     @RequestParam("pageSize") Integer pageSize) {
        Page<EsProduct> esProducts = esProductService.search(keywords, pageNum, pageSize);
        return CommonResult.success(esProducts);
    }
}
