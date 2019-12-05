package com.example.constant;

//Rabbitmq常量值
public enum RabbitConstant {

	TASK_EXPORT("task.export", "task.export-key", "amq.direct", "导出队列"),
	TASK_EXPORT_DELAYED("task.export.delayed", "task.export.delayed-key", "amq.direct", "导出异常延时队列"),
	;

	private final String queue;
	private final String routingKey;
	private final String exchange;
	private final String desc;

	RabbitConstant(String queue, String routingKey, String exchange, String desc) {
		this.queue = queue;
		this.routingKey = routingKey;
		this.exchange = exchange;
		this.desc = desc;
	}

	public String getQueue() {
		return queue;
	}

	public String getRoutingKey() {
		return routingKey;
	}

	public String getExchange() {
		return exchange;
	}

	public String getDesc() {
		return desc;
	}

}
