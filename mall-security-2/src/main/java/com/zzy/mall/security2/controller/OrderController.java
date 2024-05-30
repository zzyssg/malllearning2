package com.zzy.mall.security2.controller;

import com.zzy.mall.security2.common.api.CommonResult;
import com.zzy.mall.security2.mbg.model.OmsOrder;
import com.zzy.mall.security2.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName OrderController
 * @Author ZZy
 * @Date 2024/5/30 23:24
 * @Description
 * @Version 1.0
 */
@RestController
@RequestMapping("/order")
@Api(tags = "OrderController")
@Tag(name = "OrderController",description = "订单管理")
public class OrderController {

    @Autowired
    OrderService orderService;

    @ApiOperation("创建订单")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody OmsOrder omsOrder) {
        int count = orderService.add(omsOrder);
        if (count > 0) {
            return CommonResult.success();
        } else {
            return CommonResult.failed("创建失败！");
        }
    }

}
