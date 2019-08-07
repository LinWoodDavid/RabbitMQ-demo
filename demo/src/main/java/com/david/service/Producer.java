package com.david.service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.GetResponse;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * =================================
 * Created by David on 2019/3/29.
 * mail:    17610897521@163.com
 * 描述:
 */
@Component
public class Producer {

    @Resource
    RabbitTemplate rabbitTemplate;

    /**
     * 发送消息到延迟队列
     *
     * @param exchange   交换机
     * @param routingKey routingKey(指向对应的队列)
     * @param msg        消息
     * @param millis     延迟时间(毫秒)
     */
    public void delayQueueSend(String exchange, String routingKey, String msg, long millis) {
        MessagePostProcessor processor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration(String.valueOf(millis));
                return message;
            }
        };
    }


}
