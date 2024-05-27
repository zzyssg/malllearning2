package com.zzy.mall.rabbitmq.config;

import com.zzy.mall.rabbitmq.mq.SimpleReceive;
import com.zzy.mall.rabbitmq.mq.SimpleSend;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * @ClassName RabbitMQConfig
 * @Author ZZy
 * @Date 2024/5/25 20:52
 * @Description
 * @Version 1.0
 */
@Configuration
public class SimpleRabbitMqConfig {


    @Bean
    public Queue hello() {
        return new Queue("simple.hello");
    }

    @Bean
    public SimpleSend SimpleSend() {
        return new SimpleSend();
    }

    @Bean
    public SimpleReceive simpleReceive() {
        return new SimpleReceive();
    }


}
