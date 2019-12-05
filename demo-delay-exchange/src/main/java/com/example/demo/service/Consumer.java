package com.example.demo.service;

import com.example.demo.configurer.RabbitMQConstant;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Random;

@Component
public class Consumer {

    @Autowired
    Producer producer;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitListener(queues = "queue.test")
    public void processMessage10(String msg, Message message, Channel channel) {
        try {
            logger.info("Receive: {}", msg);
            //ack消息确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
