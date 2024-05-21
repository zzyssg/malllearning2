package com.zzy.mall.tiny.controller;

import cn.hutool.core.util.ArrayUtil;
import com.zzy.mall.tiny.common.api.CommonResult;
import com.zzy.mall.tiny.mbg.model.PmsBrand;
import com.zzy.mall.tiny.service.PmsBrandService;
import com.zzy.mall.tiny.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * @ClassName RedisController
 * @Author ZZy
 * @Date 2024/5/12 23:15
 * @Description
 * @Version 1.0
 */
@RestController
@RequestMapping("/redis")
@Api(tags = "RedisController")
@Tag(name = "RedisController", description = "Redis管理")
public class RedisController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private RedisService redisService;


    @Autowired
    private PmsBrandService brandService;


    @ApiOperation("测试简单对象的缓存操作")
    @RequestMapping(value = "/simple", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('redis:all')")
    public CommonResult simple() {
        //设置
        List<PmsBrand> brandList = brandService.list(1, 5);
        PmsBrand brand = brandList.get(0);
        String brandKey = "brand:simple:" + brand.getId();
        LOGGER.info("select brand:{}", brand);
        redisService.set(brandKey, brand);
        //查询
        PmsBrand redisBrand = (PmsBrand) redisService.get(brandKey);
        LOGGER.info("redis brand:{}", redisBrand);
        return CommonResult.success(redisBrand);
    }

    @ApiOperation("测试Set对象的缓存操作")
    @RequestMapping(value = "/set", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('redis:all')")
    public CommonResult set() {
        //查询结果集合，转为set
        List<PmsBrand> brandList = brandService.list(1, 5);
        PmsBrand[] pmsBrands = ArrayUtil.toArray(brandList, PmsBrand.class);
        String setKey = "brand:set:all";
        //操作set类方法
        redisService.sAdd(setKey, pmsBrands);
        LOGGER.info("redis set add：{}", pmsBrands);
        redisService.sRemove(setKey, brandList.get(0));
        Set<Object> objects = redisService.sMembers(setKey);
        LOGGER.info("objects from redis:{}", objects);
        return CommonResult.success(objects);
    }

    @ApiOperation("测试List对象的缓存操作")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('redis:all')")
    public CommonResult list() {
        //查询list
        List<PmsBrand> brandList = brandService.list(1, 5);
        //转换成数组Object...
        PmsBrand[] pmsBrands = ArrayUtil.toArray(brandList, PmsBrand.class);
        String listKey = "brand:list:all";
        redisService.lPushAll(listKey, pmsBrands);
        LOGGER.info("brand:list:all,{}", pmsBrands);
        //操作redis
        redisService.lRemove(listKey, 1, brandList.get(0));
        List<Object> objectListRedis = redisService.lRange(listKey, 0, 1);
        return CommonResult.success(objectListRedis);
    }
}
