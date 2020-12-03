package com.praveen.restservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.praveen.restservices.dao.PraveenLogDAO;
import com.praveen.restservices.model.PraveenLog;
import com.praveen.restservices.producer.MessageProducer;

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
