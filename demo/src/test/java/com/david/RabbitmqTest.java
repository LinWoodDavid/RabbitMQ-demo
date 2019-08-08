package com.david;

import com.david.core.RabbitMQConstant;
import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Athor weimc
 * @CreateTime 2019/8/7 11:32
 * @Description: TODO
 */
public class RabbitmqTest extends DemoApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void a() {
        for (int i = 0; i < 10; i++) {
            String msg = String.valueOf(1000000 + i);
            rabbitTemplate.convertAndSend(RabbitMQConstant.DEFAULT_EXCHANGE_NAME.getName()
                , RabbitMQConstant.SIMPLE_QUEUE_ROUTING_KEY.getName()
                , msg);
        }
        System.out.println("end");
    }

    @Test
    public void send() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            String msg = new StringBuffer("msg").append(i + 1).toString();
            rabbitTemplate.convertAndSend("direct-exchange", "consumer-pull-routing-key", msg);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}


