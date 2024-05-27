package com.zzy.mall.rabbitmq.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @ClassName SimpleReceive
 * @Author ZZy
 * @Date 2024/5/25 20:58
 * @Description
 * @Version 1.0
 */
@RabbitListener(queues = "simple.hello")
public class SimpleReceive {

    private final static Logger LOGGER = LoggerFactory.getLogger(SimpleReceive.class);


    @RabbitHandler
    public void receiveMessage(String message) {
        LOGGER.info("receive msg:{}", message);
    }
}
