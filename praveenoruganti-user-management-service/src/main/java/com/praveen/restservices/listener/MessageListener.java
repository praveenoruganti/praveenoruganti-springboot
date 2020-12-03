package com.praveen.restservices.listener;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.praveen.restservices.dao.PraveenLogDAO;
import com.praveen.restservices.model.PraveenLog;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MessageListener {
	@Autowired
	private PraveenLogDAO praveenLogDAO;

	@RabbitListener(queues = "${praveen-user-management-service.rabbitmq.queueName}")
	public void receivedMessage(String message) {
		log.info(LocalDate.now().toString());
		try {
			Thread.sleep(5000);
			PraveenLog praveenLog = new PraveenLog();
			praveenLog.setLogMessage(message);
			praveenLog.setLogDate(Date.valueOf(LocalDate.now()));
			praveenLogDAO.createLog(praveenLog);
			log.info("Message Received:" + message);
			log.info(LocalDate.now().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
