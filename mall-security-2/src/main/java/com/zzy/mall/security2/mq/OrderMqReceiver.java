package com.zzy.mall.security2.mq;

import com.zzy.mall.security2.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName OrderMqReceiver
 * @Author ZZy
 * @Date 2024/5/30 22:44
 * @Description
 * @Version 1.0
 */
@Slf4j
@Component
@RabbitListener(queues = "order.cancel.queue")
public class OrderMqReceiver {

    @Autowired
    OrderService orderService;

    @RabbitHandler
    public void receive(Long orderId) {
        log.info("收到延迟消息,orderId：{}", orderId);
        orderService.cancel(orderId);
    }


}
