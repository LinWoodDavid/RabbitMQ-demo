package com.david.service;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Argument;
import org.springframework.amqp.rabbit.annotation.Queue;
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

    private int count = 0;

/*    @RabbitListener(queues = "consumer-queue")
    public void processMessage(String msg, Message message, Channel channel) {
        logger.info("[consumer-queue] Receive: {}", msg);
        try {
            //ack消息确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    */

/*    @RabbitListener(queues = {"hello"})
    public void processMessage2(String msg, Message message, Channel channel) {
        try {
            logger.info("[hello-queue] Receive: {}", msg);
            Thread.sleep(2000);
            //ack消息确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/

/*    @RabbitListener(queues = "consumer-queue")
    public void processMessage2(String msg, Message message, Channel channel) {
        try {
            logger.info("[consumer-queue] Receive: {}", msg);
            //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);//删除当前消息
            //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);//消息重回队列
            //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);//删除当前消息(转发到队列)
            Thread.sleep(2000);
            //ack消息确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/

/*    @RabbitListener(queues = "dead-letter-queue")
    public void processMessage3(String msg, Message message, Channel channel) {
        try {
            logger.info("[dead-letter-queue] Receive: {}", msg);
            //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);//删除当前消息
            //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);//消息重回队列
            //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);//删除当前消息(转发到队列)
            //ack消息确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    @RabbitListener(queues = "test.default")
    public void processMessage10(String msg, Message message, Channel channel) {
        try {
            logger.info("[test.default] Receive: {}", msg);
            Thread.sleep(1000);
            //ack消息确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
