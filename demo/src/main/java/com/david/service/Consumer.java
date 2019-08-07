package com.david.service;

import com.david.core.RabbitMQConstant;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.GetResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * =================================
 * Created by David on 2019/3/29.
 * mail:    17610897521@163.com
 * 描述:      RabbitMQ 消费者
 */
@Component
public class Consumer {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private int count = 0;

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
            logger.info("[hello-queue] Receive: {}", msg);
            //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);//删除当前消息
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);//消息重回队列
            //ack消息确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
