package com.example.test;

import com.example.DemoTtlApplicationTests;
import com.example.constant.RabbitConstant;
import com.example.mq.Producer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class Demo extends DemoTtlApplicationTests {

    @Autowired
    Producer producer;

    @Test
    public void a() {
        producer.send(RabbitConstant.TASK_EXPORT, "循环测试");
    }
}
