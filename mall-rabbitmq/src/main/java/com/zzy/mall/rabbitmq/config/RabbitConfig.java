package com.zzy.mall.rabbitmq.config;

import com.zzy.mall.rabbitmq.domain.QueueEnum;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName RabbitConfig
 * @Author ZZy
 * @Date 2024/5/27 22:39
 * @Description
 * @Version 1.0
 */
@Configuration
public class RabbitConfig {

    //订单消息实际消费队列，绑定的交换机
    @Bean
    public DirectExchange orderExchange() {
        return ExchangeBuilder.directExchange(QueueEnum.QUEUE_ORDER_CANCEL.getExchangeName())
                .durable(true)  //是否持久化
                .build();
    }

    //订单延迟消息队列，绑定的交换机
    @Bean
    public DirectExchange orderExchangeTtl() {
        return ExchangeBuilder.directExchange(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchangeName())
                .durable(true)
                .build();
    }

    //订单实际消费队列
    @Bean
    public Queue orderQueue() {
        return new Queue(QueueEnum.QUEUE_ORDER_CANCEL.getQueueName());
    }


    //延迟消息消费队列 TODO 注意withArgument
    @Bean
    public Queue orderQueueTtl() {
        return QueueBuilder.durable(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getQueueName())
                .withArgument("x-dead-letter-exchange", QueueEnum.QUEUE_ORDER_CANCEL.getExchangeName()) //【到期后】转发的交换机
                .withArgument("x-dead-letter-routing-key", QueueEnum.QUEUE_ORDER_CANCEL.getRouterKey()) //【到期后】转发的路由键
                .build();
    }

    //将订单消息消费队列绑定到交换机
    @Bean
    Binding orderBinding(DirectExchange orderExchange, Queue orderQueue) {
        return BindingBuilder.bind(orderQueue)
                .to(orderExchange)
                .with(QueueEnum.QUEUE_ORDER_CANCEL.getRouterKey());
    }

    //将延迟消费队列绑定到交换机
    @Bean
    Binding orderBindingTtl(DirectExchange orderExchangeTtl, Queue orderQueueTtl) {
        return BindingBuilder.bind(orderQueueTtl)
                .to(orderExchangeTtl)
                .with(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRouterKey());
    }


}
