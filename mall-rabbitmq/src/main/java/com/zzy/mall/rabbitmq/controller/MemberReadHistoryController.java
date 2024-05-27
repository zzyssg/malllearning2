package com.zzy.mall.rabbitmq.controller;

import com.zzy.mall.rabbitmq.common.api.CommonResult;
import com.zzy.mall.rabbitmq.domain.MemberReadHistory;
import com.zzy.mall.rabbitmq.service.MemberReadHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName MemberReadHistoryController
 * @Author ZZy
 * @Date 2024/5/25 16:55
 * @Description
 * @Version 1.0
 */
@RestController
@RequestMapping("/memberReadHistory")
@Api(tags = "MemberReadHistoryController")
@Tag(name = "MemberReadHistoryController", description = "浏览记录")
public class MemberReadHistoryController {

    @Autowired
    MemberReadHistoryService readHistoryService;

    @ApiOperation("创建历史记录")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody MemberReadHistory readHistory) {
        int insert = readHistoryService.insert(readHistory);
        if (insert > 0) {
            return CommonResult.success();
        } else {
            return CommonResult.failed("创建浏览记录失败！");
        }
    }

    @ApiOperation("查询历史记录")
    @RequestMapping(value = "/search/{memberId}", method = RequestMethod.GET)
    public CommonResult search(@PathVariable("memberId") Long memberId) {
        List<MemberReadHistory> readHistoryByMemberId = readHistoryService.findReadHistoryByMemberId(memberId);
        return CommonResult.success(readHistoryByMemberId);
    }

    @ApiOperation("删除历史记录")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public CommonResult delete(@RequestParam List<String> ids) {
        int count = readHistoryService.deleteBatch(ids);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed("删除历史记录失败!");
        }
    }
}
