package com.zzy.mall.rabbitmq.domain;

import lombok.Getter;

/**
 * @ClassName QueueEnum
 * @Author ZZy
 * @Date 2024/5/27 22:35
 * @Description
 * @Version 1.0
 */
@Getter
public enum QueueEnum {


    //消息通知队列
    QUEUE_ORDER_CANCEL("mall.order.direct", "mall.order.cancel", "mall.order.cancel"),
    //消息通知ttl队列
    QUEUE_TTL_ORDER_CANCEL("mall.order.direct.ttl", "mall.order.cancel.ttl", "mall.order.cancel.ttl");


    String exchangeName;
    String routerKey;
    String queueName;

    QueueEnum() {
    }

    QueueEnum(String exchangeName, String routerKey, String queueName) {
        this.exchangeName = exchangeName;
        this.routerKey = routerKey;
        this.queueName = queueName;
    }




}
