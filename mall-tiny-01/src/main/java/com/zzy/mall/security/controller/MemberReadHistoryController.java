package com.zzy.mall.security.controller;

import com.zzy.mall.security.common.api.CommonResult;
import com.zzy.mall.security.nosql.mongodb.document.MemberReadHistory;
import com.zzy.mall.security.service.MemberReadHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.management.monitor.CounterMonitor;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @ClassName MemberReadHistoryController
 * @Author ZZy
 * @Date 2024/5/15 21:28
 * @Description
 * @Version 1.0
 */
@RestController
@RequestMapping("/member/readHistory")
@Api(tags = "MemberReadHistoryController")
@Tag(name = "MemberReadHistoryController", description = "会员浏览历史管理")
public class MemberReadHistoryController {

    @Autowired
    private MemberReadHistoryService readHistoryService;

    @ApiOperation("新增浏览历史")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody MemberReadHistory memberReadHistoryDto) {

        int addCount = readHistoryService.add(memberReadHistoryDto);
        if (addCount > 0) {
            return CommonResult.success(addCount);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("删除浏览历史")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public CommonResult delete(@RequestParam("ids") List<String> ids) {
        int deleteCount = readHistoryService.delete(ids);
        if (deleteCount > 0) {
            return CommonResult.success(deleteCount);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("查询浏览历史")
    @RequestMapping(value = "/search/{memberId}", method = RequestMethod.GET)
    public CommonResult search(@PathVariable Long memberId) {
        List<MemberReadHistory> readHistories = readHistoryService.search(memberId);
        return CommonResult.success(readHistories);

    }
}
