package com.praveen.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.praveen.kafka.service.ProducerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Apache Kafka Restful Service", value = "KafkaController", description = "Controller for Kafka Publishing")
@RequestMapping(value = "/rest/kafka")
public class KafkaController {

	@Autowired
	ProducerService producerService;

	@PostMapping(value = "/publish")
	@ApiOperation(value = "Publishing Message")
	public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
		this.producerService.sendMessage(message);
	}

}
