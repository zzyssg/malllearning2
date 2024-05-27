package com.zzy.mall.rabbitmq.direct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName DirectSender
 * @Author ZZy
 * @Date 2024/5/27 20:53
 * @Description
 * @Version 1.0
 */
public class DirectSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String exchangeDirect = "exchange.direct";

    private String[] keys = {"orange", "yellow", "blue"};

    private static final Logger LOGGER = LoggerFactory.getLogger(DirectSender.class);

    public void send(int index) {

        StringBuilder sb = new StringBuilder("hello to ");
        int limitIndex = index % 3;
        String key = keys[limitIndex];
        sb.append(key);
        sb.append(index + 1);
        for (int i = 0; i <= limitIndex; i++) {
            sb.append(".");
        }
        String msg = sb.toString();
        rabbitTemplate.convertAndSend(exchangeDirect, key, msg);
        LOGGER.info("sending msg: {}", msg);

    }



}
