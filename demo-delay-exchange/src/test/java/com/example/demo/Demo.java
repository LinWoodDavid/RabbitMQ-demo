package com.example.demo;

import com.example.demo.configurer.RabbitMQConstant;
import com.example.demo.service.Producer;
import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class Demo extends DemoDelayExchangeApplicationTests {

    @Autowired
    Producer producer;

    @Test
    public void a() throws InterruptedException {
        producer.delayExchangeSend(RabbitMQConstant.DEAD_EXCHANGE_NAME.getName(), RabbitMQConstant.QUEUE_KEY.getName()
                , "延迟60s", 60 * 1000);
        Thread.sleep(5000);
        producer.delayExchangeSend(RabbitMQConstant.DEAD_EXCHANGE_NAME.getName(), RabbitMQConstant.QUEUE_KEY.getName()
                , "延迟10s", 10 * 1000);

    }
}
