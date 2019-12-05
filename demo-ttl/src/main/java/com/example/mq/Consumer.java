package com.example.mq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Consumer {

    @RabbitListener(queues = "task.export")
    public void export1(String msg, Message message, Channel channel) {
        try {
            System.out.println(msg);
            //channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);//ack消息确认
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);//删除当前消息
            //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);//消息重回队列
            //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);//删除当前消息(转发到队列)
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
