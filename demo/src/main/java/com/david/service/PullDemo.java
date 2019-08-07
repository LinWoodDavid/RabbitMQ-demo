package com.david.service;

import com.david.core.RabbitMQConstant;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.GetResponse;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Athor weimc
 * @CreateTime 2019/8/7 0007 10:40
 * @Description: rabbitmq pull 模式
 */
public class PullDemo {

    /**
     * 拉取单条
     *
     * @throws IOException
     * @throws TimeoutException
     */
    public void pullOne() throws IOException, TimeoutException {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses("47.98.108.122");
        connectionFactory.setUsername("LinWood_David");
        connectionFactory.setPassword("123456");
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.createConnection();
        Channel channel = connection.createChannel(false);
        GetResponse response = channel.basicGet(RabbitMQConstant.PULL_QUEUE_NAME.getName(), false);//true:自动确认,false手动确认

        String msg = new String(response.getBody());
        long tag = response.getEnvelope().getDeliveryTag();
        System.out.println("msg: " + msg + ", tag: " + tag);
        channel.basicAck(tag, false);

        //关闭连接
        channel.close();
        connection.close();
    }


}


