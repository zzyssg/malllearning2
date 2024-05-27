package com.zzy.mall.rabbitmq.direct;

import cn.hutool.core.date.StopWatch;
import cn.hutool.core.thread.ThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName DirectReceiver
 * @Author ZZy
 * @Date 2024/5/27 20:53
 * @Description 2个消费者
 * @Version 1.0
 */
public class DirectReceiver {


    private static final Logger LOGGER = LoggerFactory.getLogger(DirectReceiver.class);

    @RabbitListener(queues = "#{directQueue1.name}")
    public void receive1(String info) {
        receive(info, 1);
    }

    @RabbitListener(queues = "#{directQueue2.name}")
    public void receive2(String info) {
        receive(info, 2);
    }

    private void receive(String info, int num) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        LOGGER.info("consumer {} is receiving {}", num,info);
        work(info);
        stopWatch.stop();
        LOGGER.info("consumer {} received in {} s", num, stopWatch.getTotalTimeSeconds());

    }

    private void work(String info) {
        char[] chars = info.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if ('.' == chars[i]) {
                ThreadUtil.sleep(1000);
            }
        }
    }

}
