package com.david.pull;

import com.david.core.RabbitMQConstant;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.GetResponse;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;

import java.io.IOException;

/**
 * @Athor weimc
 * @CreateTime 2019/8/8 17:15
 * @Description:
 */
public class Demo2 {

    public static void main(String[] args) throws IOException, InterruptedException {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses("192.168.159.133");
        connectionFactory.setUsername("David");
        connectionFactory.setPassword("123456");
        connectionFactory.setVirtualHost("/");
        Connection connection = connectionFactory.createConnection();
        Channel channel = connection.createChannel(false);
        GetResponse response = channel.basicGet(RabbitMQConstant.PULL_QUEUE_NAME.getName(), false);//true:自动确认,false手动确认
        long tag = response.getEnvelope().getDeliveryTag();
        String msg = new String(response.getBody());
        System.out.println(tag);
        System.out.println(msg);
        Thread.sleep(1000 * 60 * 10);
        channel.basicAck(tag, false);//消息确认
    }

}


