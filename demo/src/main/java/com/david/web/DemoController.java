package com.david.web;

import com.david.core.RabbitMQConstant;
import com.david.core.Result;
import com.david.core.ResultGenerator;
import com.david.service.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * =================================
 * Created by David on 2019/3/29.
 * mail:    17610897521@163.com
 * 描述:
 */
@RestController
public class DemoController {

    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Resource
    RabbitTemplate rabbitTemplate;

    @Resource
    Producer producer;

    /**
     * 发送消息
     * http://localhost:8080/simpQueue?msg=%E6%B6%88%E6%81%AF%E6%B5%8B%E8%AF%95
     *
     * @param msg 消息
     * @return
     */
    @RequestMapping("/simpQueue")
    public Result simpleQueue(String msg) {
        //发送简单消息
        rabbitTemplate.convertAndSend(RabbitMQConstant.DEFAULT_EXCHANGE_NAME.getName()
            , RabbitMQConstant.SIMPLE_QUEUE_ROUTING_KEY.getName()
            , msg);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * http://localhost:8080/pullQueue?msg=%E6%B6%88%E6%81%AF%E6%B5%8B%E8%AF%95
     *
     * @param msg
     * @return
     */
    @RequestMapping("/pullQueue")
    public Result pullQueue(String msg) {
        //发送简单消息
        rabbitTemplate.convertAndSend(RabbitMQConstant.DEFAULT_EXCHANGE_NAME.getName()
            , RabbitMQConstant.PULL_QUEUE_ROUTING_KEY.getName()
            , msg);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 延迟队列
     * 延迟10秒    http://localhost:8080/delayQueue?msg=%E6%B5%8B%E8%AF%95&millis=10000
     *
     * @param msg    消息
     * @param millis 延迟时间(毫秒)
     * @return
     */
    @RequestMapping("/delayQueue")
    public Result delayQueue(String msg, long millis) {
        //发送延迟消息
        producer.delayQueueSend(RabbitMQConstant.DEFAULT_EXCHANGE_NAME.getName(),
            RabbitMQConstant.DEAD_LETTER_QUEUE_ROUTING_KEY.getName(), msg, millis);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 生产优先级
     * http://localhost:8080/send?msg=优先级测试0&priority=0
     * http://localhost:8080/send?msg=优先级测试225&priority=225
     *
     * @param msg
     * @param priority 优先级(0~255)数越大越先执行
     * @return
     */
    @RequestMapping("/send")
    public Result send(String msg, int priority) {
        MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setPriority(priority);
                return message;
            }
        };
        rabbitTemplate.convertAndSend(RabbitMQConstant.DEFAULT_EXCHANGE_NAME.getName()
            , RabbitMQConstant.SIMPLE_QUEUE_ROUTING_KEY.getName()
            , msg, messagePostProcessor);
        return ResultGenerator.genSuccessResult();
    }

}
