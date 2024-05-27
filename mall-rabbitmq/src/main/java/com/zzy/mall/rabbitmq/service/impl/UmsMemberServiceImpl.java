package com.zzy.mall.rabbitmq.service.impl;

import cn.hutool.core.util.StrUtil;
import com.zzy.mall.rabbitmq.service.RedisService;
import com.zzy.mall.rabbitmq.service.UmsMemberService;
import com.zzy.mall.rabbitmq.common.api.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @ClassName UmsMemberServiceImpl
 * @Author ZZy
 * @Date 2024/5/24 12:37
 * @Description
 * @Version 1.0
 */
@Service
public class UmsMemberServiceImpl implements UmsMemberService {

    @Autowired
    RedisService redisService;

    @Value("${redis.key.prefix.authCode}")
    private String prefixAuthCode;

    @Value("${redis.key.expire.authCode}")
    private Long expiration;

    @Override
    public CommonResult getAuthCode(String telNum) {
        //验证非空
        if (StrUtil.isEmpty(telNum)) {
            return CommonResult.failed("手机号不能为空");
        }
        //生成随机码
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        //生成key
        String key = prefixAuthCode + telNum;
        //设置到redis，并返回
        redisService.set(key,sb.toString(),expiration);
        return CommonResult.success(sb.toString());
    }

    @Override
    public CommonResult verifyCode(String telNum, String code) {
        //检验telNum和code的合法性
        if (StrUtil.isEmpty(telNum) || StrUtil.isEmpty(code)) {
            return CommonResult.failed("手机号或者验证码为空");
        }
        //从redis中获取cacheCode
        String key = prefixAuthCode + telNum;
        String cacheCode = (String) redisService.get(key);
        //比较code和cacheCode，返回结果
        return CommonResult.success(code.equals(cacheCode));


    }


}
