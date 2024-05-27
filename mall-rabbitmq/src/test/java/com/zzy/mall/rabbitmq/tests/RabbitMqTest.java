package com.zzy.mall.rabbitmq.tests;

import cn.hutool.core.thread.ThreadUtil;
import com.zzy.mall.rabbitmq.config.SimpleRabbitMqConfig;
import com.zzy.mall.rabbitmq.mq.SimpleSend;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName RabbitMqTest
 * @Author ZZy
 * @Date 2024/5/25 21:03
 * @Description
 * @Version 1.0
 */
@Slf4j
@SpringBootTest
public class RabbitMqTest {

    @Autowired
    SimpleSend simpleSend;


    @Test
    void testSimpleMq() {
        for (int i = 0; i < 10; i++) {
            simpleSend.sendMessage(" " + i);
            log.info("send msg: " + i);
            ThreadUtil.sleep(1000);
        }
    }

}
