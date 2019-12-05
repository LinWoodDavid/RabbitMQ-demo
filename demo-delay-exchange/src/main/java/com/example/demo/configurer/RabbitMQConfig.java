package com.example.demo.configurer;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * =================================
 * Created by David on 2019/3/29.
 * mail:    17610897521@163.com
 * 描述:
 */
@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue myQueue() {
        Queue queue = new Queue(RabbitMQConstant.QUEUE_NAME.getName(), true, false, false);
        return queue;
    }

    //************************************************ 死信交换机 start ************************************************

    /**
     * 死信交换机
     *
     * @return
     */
    @Bean
    CustomExchange delayExchange() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-delayed-type", "direct");
        return new CustomExchange(RabbitMQConstant.DEAD_EXCHANGE_NAME.getName(), "x-delayed-message", true, false, arguments);
    }

    @Bean
    public Binding delayBinding() {
        return BindingBuilder.bind(myQueue()).to(delayExchange()).with(RabbitMQConstant.QUEUE_KEY.getName()).noargs();
    }
    //************************************************ 死信交换机 end ************************************************


}
