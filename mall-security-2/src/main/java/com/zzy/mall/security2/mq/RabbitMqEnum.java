package com.zzy.mall.security2.mq;

import lombok.Data;
import lombok.Getter;

/**
 * @ClassName RabbitMqEnum
 * @Author ZZy
 * @Date 2024/5/30 22:12
 * @Description
 * @Version 1.0
 */
@Getter
public enum RabbitMqEnum {

    ORDER_CANCEL_QUEUE("order.cancel.exchange","order.cancel.queue","order.cancel.queue"),
    ORDER_DELAY_CANCEL_QUEUE("order.delay.exchange","order.delay.queue","order.delay.queue");

    RabbitMqEnum(String exchangeDirect, String key, String queueName) {
        this.exchangeDirect = exchangeDirect;
        this.key = key;
        this.queueName = queueName;
    }

    private String exchangeDirect;

    private String key;

    private String queueName;


}
