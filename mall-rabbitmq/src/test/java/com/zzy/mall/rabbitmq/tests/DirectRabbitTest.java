package com.zzy.mall.rabbitmq.tests;

import cn.hutool.core.thread.ThreadUtil;
import com.zzy.mall.rabbitmq.direct.DirectSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName DirectRabbitTest
 * @Author ZZy
 * @Date 2024/5/27 21:52
 * @Description
 * @Version 1.0
 */
@SpringBootTest
public class DirectRabbitTest {


    @Autowired
    DirectSender sender;

    @Test
    void testSend() {
        int count = 10;
        for (int i = 0; i < count; i++) {
            sender.send(i);
            ThreadUtil.sleep(1000);
        }
    }




}
