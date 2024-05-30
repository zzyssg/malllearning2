package com.zzy.mall.security2.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName OrderMqSender
 * @Author ZZy
 * @Date 2024/5/30 22:39
 * @Description 消息发送方
 * @Version 1.0
 */
@Slf4j
@Component
public class OrderMqSender {

    @Autowired
    AmqpTemplate amqpTemplate;


    public void sendMessage(Long orderId,long delayTime) {
        log.info("entered orderMqSender..sendMessage");
        try {

            amqpTemplate.convertAndSend(RabbitMqEnum.ORDER_DELAY_CANCEL_QUEUE.getExchangeDirect(), RabbitMqEnum.ORDER_DELAY_CANCEL_QUEUE.getKey(), orderId, new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    message.getMessageProperties().setExpiration(String.valueOf(delayTime));
                    return message;
                }
            });
            log.info("send orderId:{}", orderId);
        } catch (Exception e) {
            log.error("发送消息时出错：{}", e.getMessage());
            e.printStackTrace();
        }
    }


}
