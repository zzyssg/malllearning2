package com.zzy.mall.rabbitmq.service.impl;

import com.zzy.mall.rabbitmq.common.api.CommonResult;
import com.zzy.mall.rabbitmq.component.CancelOrderSender;
import com.zzy.mall.rabbitmq.dto.OrderParam;
import com.zzy.mall.rabbitmq.service.OmsPortalOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName OmsPortalOrderServiceImpl
 * @Author ZZy
 * @Date 2024/5/27 23:09
 * @Description
 * @Version 1.0
 */
@Service
public class OmsPortalOrderServiceImpl implements OmsPortalOrderService {


    @Autowired
    CancelOrderSender cancelOrderSender;
    private static final Logger LOGGER = LoggerFactory.getLogger(OmsPortalOrderServiceImpl.class);

    @Override
    public CommonResult generateOrder(OrderParam orderParam) {
        //TODO 创建订单
        LOGGER.info("正在生成订单...");
        //订单生成后，发送一个延迟消息。用户一定时间内没有付款，则取消订单
        Long orderId = 1L;
        sendDelayMessageCancelOrder(orderId);
        return CommonResult.success("下单成功!");

    }

    private void sendDelayMessageCancelOrder(Long orderId) {
        //获取订单超时时间：60S
        long delayTime = 60 * 1000;
        //发送延迟消息
        cancelOrderSender.sendMessage(orderId, delayTime);
    }

    @Override
    public void cancelOrder(Long orderId) {
        //TODO 执行订单取消操作
        LOGGER.info("orderId:{} 已经被取消订单.");
    }
}
