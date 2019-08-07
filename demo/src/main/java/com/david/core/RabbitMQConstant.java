package com.david.core;

/**
 * =================================
 * Created by David on 2019/3/29.
 * mail:    17610897521@163.com
 * 描述:      RabbitMQ 常量
 */

public enum RabbitMQConstant {

    //默认交换机
    DEFAULT_EXCHANGE_NAME("direct-exchange"),

    SIMPLE_QUEUE_NAME("hello"),

    PULL_QUEUE_NAME("consumer-pull-queue"),

    SIMPLE_QUEUE_ROUTING_KEY("hello-routing-key"),

    PULL_QUEUE_ROUTING_KEY("consumer-pull-routing-key"),

    //死信队列名
    DEAD_LETTER_QUEUE_NAME("dead-letter-queue"),


    //死信队列 routing-key
    DEAD_LETTER_QUEUE_ROUTING_KEY("dead-letter-queue-routing-key"),

    //消费队列名
    CONSUMER_QUEUE_NAME("consumer-queue"),

    //消费队列 routing-key
    CONSUMER_QUEUE_ROUTING_KEY("consumer-queue-routing-key");

    private final String name;

    RabbitMQConstant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
