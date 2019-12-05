package com.david;

import com.david.core.RabbitMQConstant;
import com.david.service.Producer;
import org.junit.Test;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import javax.annotation.Resource;

public class ProducerTest extends DemoApplicationTests {

    @Resource
    Producer producer;

    @Resource
    RabbitTemplate rabbitTemplate;

    @Test
    public void sendTest() {
        rabbitTemplate.convertAndSend("test.default-key", "消息1");
        rabbitTemplate.convertAndSend("(AMQP default)", "test.default-key", "消息2");
        rabbitTemplate.convertAndSend("test.default-key", "消息3");
        rabbitTemplate.convertAndSend("(AMQP default)", "test.default-key", "消息4");
    }

    @Test
    public void sendHello() {
        for (int i = 0; i < 10000; i++) {
            rabbitTemplate.convertAndSend(RabbitMQConstant.DEFAULT_EXCHANGE_NAME.getName(), RabbitMQConstant.SIMPLE_QUEUE_ROUTING_KEY.getName()
                    , "消息" + i);
        }
        System.out.println("end");
    }

    @Test
    public void send() {
        MessagePostProcessor processor1 = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration(String.valueOf(60 * 1000));
                //message.getMessageProperties().setDelay(60 * 1000);
                return message;
            }
        };
        rabbitTemplate.convertAndSend(RabbitMQConstant.DEAD_EXCHANGE_NAME.getName(), RabbitMQConstant.DEAD_LETTER_QUEUE_ROUTING_KEY.getName()
                , "60s过期消息", processor1);
        MessagePostProcessor processor2 = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration(String.valueOf(10 * 1000));
                //message.getMessageProperties().setDelay(10 * 1000);
                message.getMessageProperties().setDelay();
                return message;
            }
        };
        rabbitTemplate.convertAndSend(RabbitMQConstant.DEAD_EXCHANGE_NAME.getName(), RabbitMQConstant.DEAD_LETTER_QUEUE_ROUTING_KEY.getName()
                , "10s过期消息", processor2);
        System.out.println("end");
    }

}
