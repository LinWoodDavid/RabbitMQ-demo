package com.david.service;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ConsumerHello {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitListener(queues = {"hello"})
    public void processMessage1(String msg, Message message, Channel channel) {
        try {
            logger.info("[consumer1] Receive: {}", msg);
            Thread.sleep(1000);
            //ack消息确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = {"hello"})
    public void processMessage2(String msg, Message message, Channel channel) {
        try {
            logger.info("[consumer2] Receive: {}", msg);
            Thread.sleep(1000);
            //ack消息确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = {"hello"})
    public void processMessage3(String msg, Message message, Channel channel) {
        try {
            logger.info("[consumer3] Receive: {}", msg);
            Thread.sleep(1000);
            //ack消息确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = {"hello"})
    public void processMessage4(String msg, Message message, Channel channel) {
        try {
            logger.info("[consumer4] Receive: {}", msg);
            Thread.sleep(1000);
            //ack消息确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = {"hello"})
    public void processMessage5(String msg, Message message, Channel channel) {
        try {
            logger.info("[consumer5] Receive: {}", msg);
            Thread.sleep(1000);
            //ack消息确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = {"hello"})
    public void processMessage6(String msg, Message message, Channel channel) {
        try {
            logger.info("[consumer6] Receive: {}", msg);
            Thread.sleep(1000);
            //ack消息确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = {"hello"})
    public void processMessage7(String msg, Message message, Channel channel) {
        try {
            logger.info("[consumer7] Receive: {}", msg);
            Thread.sleep(1000);
            //ack消息确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = {"hello"})
    public void processMessage8(String msg, Message message, Channel channel) {
        try {
            logger.info("[consumer8] Receive: {}", msg);
            Thread.sleep(1000);
            //ack消息确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = {"hello"})
    public void processMessage9(String msg, Message message, Channel channel) {
        try {
            logger.info("[consumer9] Receive: {}", msg);
            Thread.sleep(1000);
            //ack消息确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = {"hello"})
    public void processMessage10(String msg, Message message, Channel channel) {
        try {
            logger.info("[consumer10] Receive: {}", msg);
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
