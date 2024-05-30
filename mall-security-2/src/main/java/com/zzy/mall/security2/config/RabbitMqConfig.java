package com.zzy.mall.security2.config;

import com.zzy.mall.security2.mq.RabbitMqEnum;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName RabbitMqConfig
 * @Author ZZy
 * @Date 2024/5/30 22:05
 * @Description 消息队列配置类，配置交换器、队列、binding对象
 * @Version 1.0
 */
@Configuration
public class RabbitMqConfig {

    @Bean
    public DirectExchange exchangeDirect() {
        return ExchangeBuilder.
                directExchange(RabbitMqEnum.ORDER_CANCEL_QUEUE.getExchangeDirect())
                .durable(true)
                .build();
    }

    @Bean
    public DirectExchange exchangeDirectDelay() {
        return ExchangeBuilder
                .directExchange(RabbitMqEnum.ORDER_DELAY_CANCEL_QUEUE.getExchangeDirect())
                .durable(true)
                .build();
    }

    @Bean
    public Queue queueCancel() {
        return new Queue(RabbitMqEnum.ORDER_CANCEL_QUEUE.getQueueName());
    }

    //    @Bean
//    public Queue queueDelay() {
//        return new AnonymousQueue();
//    }
    //死信队列 ： 消息过期或者队列满了进入死信队列
    @Bean
    public Queue queueDelay() {
        return QueueBuilder
                .durable(RabbitMqEnum.ORDER_DELAY_CANCEL_QUEUE.getQueueName())
                .withArgument("x-dead-letter-exchange", RabbitMqEnum.ORDER_CANCEL_QUEUE.getExchangeDirect())
                .withArgument("x-dead-letter-routing-key", RabbitMqEnum.ORDER_CANCEL_QUEUE.getKey())
                .build();
    }



    @Bean
    public Binding bindingCancel(DirectExchange exchangeDirect,Queue queueCancel) {
        Binding binding = BindingBuilder
                .bind(queueCancel)
                .to(exchangeDirect)
                .with(RabbitMqEnum.ORDER_CANCEL_QUEUE.getKey());
        return binding;
    }

    @Bean
    public Binding bindingDelayCancel(DirectExchange exchangeDirectDelay, Queue queueDelay) {
        Binding binding = BindingBuilder
                .bind(queueDelay)
                .to(exchangeDirectDelay)
                .with(RabbitMqEnum.ORDER_DELAY_CANCEL_QUEUE.getKey());
        return binding;
    }

}
