package com.zzy.mall.rabbitmq.component;

import com.zzy.mall.rabbitmq.service.OmsPortalOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName CancelOrderReceiver
 * @Author ZZy
 * @Date 2024/5/27 23:04
 * @Description
 * @Version 1.0
 */
@Component
@RabbitListener(queues = "mall.order.cancel")
public class CancelOrderReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(CancelOrderSender.class);

    @Autowired
    private OmsPortalOrderService orderService;

    @RabbitHandler
    public void handle(Long orderId) {
        LOGGER.info("receive delay message orderId : {}", orderId);
        orderService.cancelOrder(orderId);

    }


}
