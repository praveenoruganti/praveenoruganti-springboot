package com.praveen.billing.controller;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.praveen.billing.service.BillingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Flipkart Billing Restful Service", value = "BillingController", description = "Controller for Flipkart Billing Service")
@RequestMapping("/rest/billingservice")
public class BillingController {
	
	@Autowired
	BillingService billingService;
	
	@PostMapping("/billingorder")
	@ApiOperation(value = "Submit Billing Order")
	public ResponseEntity<String> billingOrder(@Valid @RequestBody String orderid, UriComponentsBuilder builder) {
		try {
			String msg=billingService.billingOrder(orderid);
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.setLocation(builder.path("/rest/billingservice/billingorder/").buildAndExpand(orderid).toUri());
			return  ResponseEntity.status(HttpStatus.CREATED).body(msg);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(),e.getCause());
		}
		
		

	}

}
