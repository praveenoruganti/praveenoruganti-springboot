package com.praveen.rabbitmq.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.praveen.rabbitmq.dao.PraveenLogDAO;
import com.praveen.rabbitmq.model.PraveenLog;
import com.praveen.rabbitmq.producer.MessageProducer;

@RestController
@RequestMapping(value = "/rest/logmessages")
public class MessageController {

	@Autowired
	private MessageProducer messageProducer;
	@Autowired
	private PraveenLogDAO praveenLogDAO;

	@GetMapping("/produce")
	public void produce(@RequestParam String message) {
		messageProducer.produceMsg(message);

	}

	@GetMapping("/getLogMessages")
	public List<PraveenLog> consume() {
		return praveenLogDAO.getAllLogs();
	}

}
