package com.zzy.mall.rabbitmq.controller;

import com.zzy.mall.rabbitmq.common.api.CommonResult;
import com.zzy.mall.rabbitmq.dto.OrderParam;
import com.zzy.mall.rabbitmq.service.OmsPortalOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName OmsPortalOrderController
 * @Author ZZy
 * @Date 2024/5/27 23:18
 * @Description
 * @Version 1.0
 */
@RestController
@RequestMapping("/order")
@Api(tags = "OmsPortalOrderController")
@Tag(name = "OmsPortalOrderController", description = "订单管理")
public class OmsPortalOrderController {

    @Autowired
    OmsPortalOrderService orderService;

    @ApiOperation("创建订单")
    @RequestMapping(value = "/createOrder", method = RequestMethod.GET)
    public CommonResult createOrder() {
        return orderService.generateOrder(new OrderParam());
    }
}
