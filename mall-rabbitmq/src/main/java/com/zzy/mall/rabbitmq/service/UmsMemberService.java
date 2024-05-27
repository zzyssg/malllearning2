package com.zzy.mall.rabbitmq.service;

import com.zzy.mall.rabbitmq.common.api.CommonResult;

/**
 * @ClassName UmsMemberService
 * @Author ZZy
 * @Date 2024/5/24 12:37
 * @Description
 * @Version 1.0
 */
public interface UmsMemberService {


    CommonResult getAuthCode(String telNum);

    CommonResult verifyCode(String tenNum, String code);
}
