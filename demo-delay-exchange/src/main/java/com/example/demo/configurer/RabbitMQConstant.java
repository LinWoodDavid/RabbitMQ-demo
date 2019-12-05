package com.example.demo.configurer;

/**
 * =================================
 * Created by David on 2019/3/29.
 * mail:    17610897521@163.com
 * 描述:      RabbitMQ 常量
 */

public enum RabbitMQConstant {

    //死信交换机
    DEAD_EXCHANGE_NAME("dead-direct-exchange"),

    QUEUE_NAME("queue.test"),
    QUEUE_KEY("queue.test-key"),

    ;

    private final String name;

    RabbitMQConstant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
