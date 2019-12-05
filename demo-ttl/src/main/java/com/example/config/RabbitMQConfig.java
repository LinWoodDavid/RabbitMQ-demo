package com.example.config;

import com.example.constant.RabbitConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
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

	//声明交换机
	@Bean
	DirectExchange defaultExchange() {
		return new DirectExchange(RabbitConstant.TASK_EXPORT.getExchange(), true, false);
	}

	//声明导出队列
	@Bean
	public Queue taskExportQueue() {
		Map<String, Object> arguments = new HashMap<>();
		arguments.put("x-dead-letter-exchange", RabbitConstant.TASK_EXPORT_DELAYED.getExchange());
		arguments.put("x-dead-letter-routing-key", RabbitConstant.TASK_EXPORT_DELAYED.getRoutingKey());
		return new Queue(RabbitConstant.TASK_EXPORT.getQueue(), true, false, false, arguments);
	}

	//绑定导出队列
	@Bean
	public Binding bindingTaskExportQueue() {
		return BindingBuilder.bind(taskExportQueue()).to(defaultExchange()).with(RabbitConstant.TASK_EXPORT.getRoutingKey());
	}

	//声明ttl队列
	@Bean
	public Queue taskExportDelayedQueue() {
		Map<String, Object> arguments = new HashMap<>();
		arguments.put("x-dead-letter-exchange", RabbitConstant.TASK_EXPORT.getExchange());
		arguments.put("x-dead-letter-routing-key", RabbitConstant.TASK_EXPORT.getRoutingKey());
		arguments.put("x-message-ttl", 60 * 1000);
		return new Queue(RabbitConstant.TASK_EXPORT_DELAYED.getQueue(), true, false, false, arguments);
	}

	//绑定导出队列
	@Bean
	public Binding bindingTaskExportDelayedQueue() {
		return BindingBuilder.bind(taskExportDelayedQueue()).to(defaultExchange()).with(RabbitConstant.TASK_EXPORT_DELAYED.getRoutingKey());
	}

}
