package com.zzy.mall.security2.service.impl;

import com.zzy.mall.security2.mbg.model.OmsOrder;
import com.zzy.mall.security2.mq.OrderMqSender;
import com.zzy.mall.security2.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName OrderServiceImpl
 * @Author ZZy
 * @Date 2024/5/30 22:38
 * @Description
 * @Version 1.0
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMqSender orderMqSender;


    @Override
    public int add(OmsOrder omsOrder) {
        //TODO 创建订单
        log.info("创建订单：{}",11);
        //发送延时取消订单的消息
        sendDelayMessage(11);
        return 1;
    }

    private void sendDelayMessage(long orderId) {
        orderMqSender.sendMessage(orderId, 3 * 1000);
    }

    @Override
    public void cancel(Long id) {
        //TODO 取消订单
        log.info("取消订单{}...", id);
    }
}
