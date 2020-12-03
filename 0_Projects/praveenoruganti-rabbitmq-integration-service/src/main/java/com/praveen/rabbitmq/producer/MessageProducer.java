package com.praveen.rabbitmq.producer;

import java.time.LocalDate;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MessageProducer {

	@Autowired
	  private AmqpTemplate amqpTemplate;

	@Value("${praveenoruganti-rabbitmq-integration-service.rabbitmq.queueName}")
	private String queueName;

	@Value("${praveenoruganti-rabbitmq-integration-service.rabbitmq.topicExchange}")
	private String topicExchange;

	public void produceMsg(String msg){
		log.info(LocalDate.now().toString());
	    amqpTemplate.convertAndSend(topicExchange, queueName, msg);
	    log.info("Send msg = " + msg);
	    log.info(LocalDate.now().toString());
	  }
}
