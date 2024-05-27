package com.zzy.mall.rabbitmq.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ArrayUtil;
import com.zzy.mall.rabbitmq.common.api.CommonResult;
import com.zzy.mall.rabbitmq.mbg.model.PmsBrand;
import com.zzy.mall.rabbitmq.service.PmsBrandService;
import com.zzy.mall.rabbitmq.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName PmsBrandController
 * @Author ZZy
 * @Date 2024/5/21 23:13
 * @Description
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/brand")
@Api(tags = "PmsBrandController")
@Tag(name = "PmsBrandController", description = "品牌管理")
public class PmsBrandController {

    @Autowired
    PmsBrandService brandService;

    @Autowired
    RedisService redisService;

    @ApiOperation("查询品牌")
    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('brand:list')")
    public CommonResult list(@PathVariable("id") Long id) {
        PmsBrand pmsBrand = brandService.listById(id);
        return CommonResult.success(pmsBrand);
    }

    @ApiOperation("更新品牌")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('brand:list')")
    public CommonResult upate(@RequestBody PmsBrand pmsBrand) {
        int count = brandService.update(pmsBrand.getId(), pmsBrand);
        if (count > 0) {

            return CommonResult.success(pmsBrand);
        } else {
            return CommonResult.failed("更新失败");
        }
    }

    @ApiOperation("创建品牌")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('brand:list')")
    public CommonResult create(@RequestBody PmsBrand brand) {
        int insert = brandService.insert(brand);
        if (insert > 0) {
            return CommonResult.success(insert);

        } else {
            return CommonResult.failed("创建失败");
        }
    }

    @ApiOperation("删除品牌")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('brand:list')")
    public CommonResult delete(@PathVariable("id") Long id) {
        int delete = brandService.delete(id);
        if (delete > 0) {
            return CommonResult.success(delete);

        } else {
            return CommonResult.failed("创建失败");
        }
    }

    @ApiOperation("查询全部品牌")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('brand:listAll')")
    public CommonResult listAll() {
        return CommonResult.success("test listAll");
    }


    @ApiOperation("测试简单缓存")
    @RequestMapping(value = "/test/simple", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('brand:list')")
    public CommonResult testSimple() {
        //TODO 判断下返回的数据是否是第一页的5条数据
        List<PmsBrand> brandList = brandService.list(1, 5);
        PmsBrand brand = brandList.get(0);
        //设置到redis里
        String key = "brand:simple:" + brand.getId();
        redisService.set(key, brand);
        //从redis中取出
        log.info("添加简单brand对象到缓存：{}", brand);
        PmsBrand brandFromRedis = (PmsBrand) redisService.get(key);
        log.info("从redis中获取brand对象：{}", brandFromRedis);
        return CommonResult.success(brandFromRedis);

    }

    @ApiOperation("测试Set结构")
    @RequestMapping(value = "/test/set", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('brand:list')")
    public CommonResult testSet() {
        //TODO 如何把list转为set
        List<PmsBrand> pmsBrandList = brandService.list(1, 5);
        String key = "brand:set:all";
        redisService.sAdd(key, (Object[]) ArrayUtil.toArray(pmsBrandList, PmsBrand.class));
        Long aLong = redisService.sRemove(key, pmsBrandList.get(0));
        return CommonResult.success(aLong);
    }

    @ApiOperation("测试Hash结构")
    @RequestMapping(value = "/test/hash", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('brand:list')")
    public CommonResult testHash() {
        List<PmsBrand> pmsBrandList = brandService.list(1, 5);
        PmsBrand brand = pmsBrandList.get(0);
        String key = "brand:map:" + brand.getId();
        Map<String, Object> map = BeanUtil.beanToMap(brand);
        redisService.hSetAll(key, map);
        Map<Object, Object> cacheMap = redisService.hGetAll(key);
        return CommonResult.success(cacheMap);
    }

    @ApiOperation("测试List结构")
    @RequestMapping(value = "/test/list", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('brand:list')")
    public CommonResult testList() {
        List<PmsBrand> pmsBrandList = brandService.list(1, 5);
        String key = "brand:list:all";
        //TODO 添加的时候，不能直接添加为list，而是需要以数组的形式添加
        redisService.lPushAll(key, ArrayUtil.toArray(pmsBrandList, PmsBrand.class));
        redisService.lRemove(key, 1, pmsBrandList.get(0));
        List<Object> cacheBrandList = redisService.lRange(key, 0, 2);
        return CommonResult.success(cacheBrandList);
    }

}
