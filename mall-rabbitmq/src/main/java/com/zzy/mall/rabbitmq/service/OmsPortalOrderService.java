package com.zzy.mall.rabbitmq.service;

import com.zzy.mall.rabbitmq.common.api.CommonResult;
import com.zzy.mall.rabbitmq.dto.OrderParam;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName OmsPortalOrderService
 * @Author ZZy
 * @Date 2024/5/27 23:08
 * @Description
 * @Version 1.0
 */
public interface OmsPortalOrderService {

    CommonResult generateOrder(OrderParam orderParam);

    @Transactional
    void cancelOrder(Long orderId);

}
