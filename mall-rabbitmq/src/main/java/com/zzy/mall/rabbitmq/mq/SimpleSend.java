package com.zzy.mall.rabbitmq.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName SimpleSend
 * @Author ZZy
 * @Date 2024/5/25 20:56
 * @Description
 * @Version 1.0
 */

public class SimpleSend {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleSend.class);

    private static final String QUEUE_ROUTER_KEY = "simple.hello";

    @Autowired
    RabbitTemplate rabbitTemplate;


    public void sendMessage(String msg) {
        rabbitTemplate.convertAndSend(QUEUE_ROUTER_KEY, "test mq send msg" + msg);
    }


}
