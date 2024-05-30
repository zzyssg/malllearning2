package com.zzy.mall.security2.controller;

import com.zzy.mall.security2.common.api.CommonResult;
import com.zzy.mall.security2.nosql.MemberReadHistory;
import com.zzy.mall.security2.service.MemberReadHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName MemberReadHistoryController
 * @Author ZZy
 * @Date 2024/5/30 21:06
 * @Description
 * @Version 1.0
 */
@RestController
@RequestMapping("/readHistory")
@Api(tags = "MemberReadHistoryController")
@Tag(name = "MemberReadHistoryController", description = "浏览历史")
public class MemberReadHistoryController {

    @Autowired
    MemberReadHistoryService readHistoryService;

    @ApiOperation("查询浏览记录")
    @RequestMapping(value = "/search/{memberId}", method = RequestMethod.GET)
    public CommonResult search(@PathVariable Long memberId) {
        List<MemberReadHistory> memberReadList = readHistoryService.findMemberReadList(memberId);
        return CommonResult.success(memberReadList);
    }

    @ApiOperation("删除浏览历史")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult delete(@PathVariable String id) {
        readHistoryService.deleteById(id);
        return CommonResult.success("删除成功！");
    }

    @ApiOperation("批量删除浏览历史")
    @RequestMapping(value = "/deleteBatch", method = RequestMethod.GET)
    public CommonResult deleteBatch(@RequestParam("ids") List<String> ids) {
        readHistoryService.deleteBatch(ids);
        return CommonResult.success("批量删除成功！");
    }

    @ApiOperation("新增浏览历史")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody MemberReadHistory readHistory) {
        int count = readHistoryService.add(readHistory);
        if (count > 0) {
            return CommonResult.success("新增浏览历史成功！");
        } else {
            return CommonResult.failed("新增浏览历史失败！");
        }
    }
}
