package com.david.pull;

import com.alibaba.fastjson.JSON;
import com.david.core.RabbitMQConstant;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.GetResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Athor weimc
 * @CreateTime 2019/8/8 9:52
 * @Description: 批量拉取任务
 */
public class PullTask implements Runnable {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ConcurrentHashMap<Long, String> lockMap;

    @Override
    public void run() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses("192.168.159.133");
        connectionFactory.setUsername("David");
        connectionFactory.setPassword("123456");
        connectionFactory.setVirtualHost("/");
        Connection connection = connectionFactory.createConnection();
        Channel channel = connection.createChannel(false);

        List<MessageBody> list = new ArrayList<>();
        try {
            while (true) {
                GetResponse response = channel.basicGet(RabbitMQConstant.PULL_QUEUE_NAME.getName(), false);//true:自动确认,false手动确认
                if (null == response) {
                    if (list.size() > 0) {
                        for (MessageBody messageBody : list) {
                            //lockMap.remove(messageBody.getTag());//解锁
                            channel.basicAck(messageBody.getTag(), false);//消息确认
                        }
                        logger.info("list size:{} ,data: {}", list.size(), JSON.toJSONString(list));
                        list.clear();
                    }
                    continue;
                }
                long tag = response.getEnvelope().getDeliveryTag();
                String msg = new String(response.getBody());
/*                if (!StringUtils.isEmpty(lockMap.get(tag))) {//不为空
                    continue;
                }*/
                list.add(new MessageBody(tag, msg));
            }
        } catch (IOException e) {
            logger.error("异常");
        }
        //关闭连接
        try {
            if (null != channel) {
                channel.close();
            }
            if (null != connection) {
                connection.close();
            }
        } catch (IOException e) {
            logger.error("关闭连接 IOException");
        } catch (TimeoutException e) {
            logger.error("关闭连接 TimeoutException");
        }
    }

    public PullTask(ConcurrentHashMap<Long, String> lockMap) {
        this.lockMap = lockMap;
    }

    public ConcurrentHashMap<Long, String> getLockMap() {
        return lockMap;
    }

    public void setLockMap(ConcurrentHashMap<Long, String> lockMap) {
        this.lockMap = lockMap;
    }
}


