package com.zzy.mall.rabbitmq.controller;

import cn.hutool.core.thread.ThreadUtil;
import com.zzy.mall.rabbitmq.common.api.CommonResult;
import com.zzy.mall.rabbitmq.direct.DirectSender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName RabbitController
 * @Author ZZy
 * @Date 2024/5/27 22:07
 * @Description
 * @Version 1.0
 */
@RestController
@RequestMapping("/queue")
@Api(tags = "RabbitController")
@Tag(name = "RabbitController", description = "消息队列")
public class RabbitController {

    @Autowired
    DirectSender sender;

    @ApiOperation("路由模式")
    @RequestMapping(value = "/router", method = RequestMethod.GET)
    public CommonResult router() {
        int times = 10;
        for (int i = 0; i < 10; i++) {
            sender.send(i);
            ThreadUtil.sleep(1000);
        }
        return CommonResult.success(times);
    }
}
