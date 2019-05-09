package com.david.service;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * =================================
 * Created by David on 2019/3/29.
 * mail:    17610897521@163.com
 * 描述:      RabbitMQ 消费者
 */
@Component
public class Consumer {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private int a = 0;

    @RabbitListener(queues = "consumer-queue")
    public void processMessage(String msg, Message message, Channel channel) {
        logger.info("[consumer-queue] Receive: {}", msg);
        try {
            //ack消息确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "hello")
    public void processMessage2(String msg, Message message, Channel channel) {
        try {
            channel.basicQos(1, true);
            logger.info("[hello] Receive: {}", msg);
            Thread.sleep(5000);
            //ack消息确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
