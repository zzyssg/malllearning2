package com.zzy.mall.rabbitmq.controller;

import com.zzy.mall.rabbitmq.common.api.CommonResult;
import com.zzy.mall.rabbitmq.domain.EsProduct;
import com.zzy.mall.rabbitmq.service.EsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName EsProductController
 * @Author ZZy
 * @Date 2024/5/25 12:45
 * @Description
 * @Version 1.0
 */
@RestController
@RequestMapping("/esProduct")
@Api(tags = "EsProductController")
@Tag(name = "EsProductController", description = "ElasticSearch搜索")
public class EsProductController {

    @Autowired
    EsProductService esProductService;


    @ApiOperation("导入数据到ES")
    @RequestMapping(value = "/importAll", method = RequestMethod.GET)
    public CommonResult importAll() {
        int count = esProductService.importAll();
        if (count > 0) {
            return CommonResult.success();
        } else {
            return CommonResult.failed("导入数据到ES中失败");
        }
    }

    @ApiOperation("新增数据")
    @RequestMapping("/add/{id}")
    public CommonResult add(@PathVariable Long id) {
        EsProduct esProduct = esProductService.create(id);
        if (esProduct != null) {
            return CommonResult.success(esProduct);
        } else {
            return CommonResult.failed("新增数据到ES中失败！");
        }
    }

    @ApiOperation("删除数据")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult delete(@PathVariable Long id) {
        esProductService.deleteById(id);
        return CommonResult.success();
    }

    @ApiOperation("批量删除数据")
    @RequestMapping(value = "/deleteBatch", method = RequestMethod.GET)
    public CommonResult deleteBatch(@RequestParam("ids") List<Long> ids) {
        int count = esProductService.deleteBatch(ids);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed("批量删除数据失败！");
        }
    }

    @ApiOperation("查询")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public CommonResult search(@RequestParam("keyword") String keyword,
                               @RequestParam("pageNum") Integer pageNum,
                               @RequestParam("pageSize") Integer pageSize) {
        Page<EsProduct> byNameOrSubTitleOrKeywords = esProductService.findByNameOrSubTitleOrKeywords(keyword, pageNum, pageSize);
        return CommonResult.success(byNameOrSubTitleOrKeywords);
    }
}
