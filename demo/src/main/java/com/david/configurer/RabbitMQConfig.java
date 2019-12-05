package com.david.configurer;

import com.david.core.RabbitMQConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

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

/*    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private String port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.publisher-confirms}")
    private Boolean publisherConfirms;

    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;

    //创建工厂连接
    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(this.host);
        connectionFactory.setUsername(this.username);
        connectionFactory.setPassword(this.password);
        connectionFactory.setVirtualHost(this.virtualHost);
        connectionFactory.setPublisherConfirms(this.publisherConfirms); //必须要设置
        return connectionFactory;
    }

    //rabbitmq的模板配置
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON) //必须是prototype类型
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(this.connectionFactory());
        //template.setConfirmCallback(); 设置消息确认
        //template.setReturnCallback();
        return template;
    }*/

    /**
     * 声明默认交换机    点对点(一对一)
     *
     * @return
     */
    @Bean
    DirectExchange defaultExchange() {
        return new DirectExchange(RabbitMQConstant.DEFAULT_EXCHANGE_NAME.getName(), true, false);
    }

    /**
     * 声明简单队列 "hello"
     *
     * @return
     */
    @Bean
    public Queue simpleQueue() {
        Map<String, Object> arguments = new HashMap<>();
        return new Queue(RabbitMQConstant.SIMPLE_QUEUE_NAME.getName(), true, false, false, arguments);
    }

    @Bean
    public Binding bindingSimpleQueue() {
        //队列绑定到exchange上，再绑定好路由键
        return BindingBuilder.bind(simpleQueue()).to(defaultExchange()).with(RabbitMQConstant.SIMPLE_QUEUE_ROUTING_KEY.getName());
    }

    @Bean
    public Queue testQueue() {
        Map<String, Object> arguments = new HashMap<>();
        return new Queue("test.default", true, false, false, arguments);
    }

    @Bean
    public Binding bindingTestQueue() {
        //队列绑定到exchange上，再绑定好路由键
        return BindingBuilder.bind(testQueue()).to(new DirectExchange(, true, false)).with("test.default-key");
    }

    /**
     * 声明用于拉取的队列 "consumer-pull-queue"
     *
     * @return
     */
    @Bean
    public Queue pullQueue() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-max-priority", 225);
        return new Queue(RabbitMQConstant.PULL_QUEUE_NAME.getName(), true, false, false, arguments);
    }

    @Bean
    public Binding bindingPullQueue() {
        //队列绑定到exchange上，再绑定好路由键
        return BindingBuilder.bind(pullQueue()).to(defaultExchange()).with(RabbitMQConstant.PULL_QUEUE_ROUTING_KEY.getName());
    }

    //************************************************ 延迟队列 start ************************************************

    /**
     * 声明消费者队列
     *
     * @return
     */
    @Bean
    public Queue consumerQueue() {
        return new Queue(RabbitMQConstant.CONSUMER_QUEUE_NAME.getName(), true, false, false);
    }

    /**
     * 将消费者队列绑定到交换机上
     *
     * @return
     */
    @Bean
    public Binding binding() {
        //队列绑定到exchange上，再绑定好路由键
        return BindingBuilder.bind(consumerQueue()).to(defaultExchange()).with(RabbitMQConstant.CONSUMER_QUEUE_ROUTING_KEY.getName());
    }

    /**
     * 声明死信队列
     *
     * @return
     */
    @Bean
    public Queue deadLetterQueue() {
        Map<String, Object> arguments = new HashMap<>();
        //死信队列交换机
        arguments.put("x-dead-letter-exchange", RabbitMQConstant.DEFAULT_EXCHANGE_NAME.getName());
        //死信队列 routing-key
        arguments.put("x-dead-letter-routing-key", RabbitMQConstant.CONSUMER_QUEUE_ROUTING_KEY.getName());
        //创建死信队列
        Queue queue = new Queue(RabbitMQConstant.DEAD_LETTER_QUEUE_NAME.getName(), true, false, false, arguments);
        return queue;
    }

    /**
     * 将死信队列绑定到交换机上
     *
     * @return
     */
    @Bean
    public Binding directBinding() {
        return BindingBuilder.bind(deadLetterQueue()).to(defaultExchange()).with(RabbitMQConstant.DEAD_LETTER_QUEUE_ROUTING_KEY.getName());
    }

    //************************************************ 延迟队列 end ************************************************
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
        //return new DirectExchange(RabbitMQConstant.DEFAULT_EXCHANGE_NAME.getName(), true, false);
    }

    @Bean
    public Binding delayBinding() {
        return BindingBuilder.bind(deadLetterQueue()).to(delayExchange()).with(RabbitMQConstant.DEAD_LETTER_QUEUE_ROUTING_KEY.getName()).noargs();
    }
    //************************************************ 死信交换机 end ************************************************


}
