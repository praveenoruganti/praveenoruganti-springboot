package com.praveen.ordermanagement.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.praveen.ordermanagement.service.OrderManagementService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Flipkart Order Management Restful Service", value = "OrderManagementController", description = "Controller for Flipkart Order Management Service")
@RequestMapping("/rest/flipkartordermanagement")
public class OrderManagementController {
	@Autowired
	OrderManagementService orderManagementService;
	@Autowired
	FeignBillingServiceProxy billingserviceproxy;
	

	@PostMapping("/feign/submitorder")
	public ResponseEntity<String> createOrderFeign(@Valid @RequestBody String orderid, UriComponentsBuilder builder) {
		try {
			String msg=billingserviceproxy.createOrder(orderid);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/rest/flipkartordermanagement/submitorder/").buildAndExpand(orderid).toUri());
			return  ResponseEntity.status(HttpStatus.CREATED).body(msg);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(),e.getCause());
		}

	}

	@PostMapping("/submitorder")
	@ApiOperation(value = "Create new order")
	public ResponseEntity<String> createOrder(@Valid @RequestBody String orderid, UriComponentsBuilder builder) {
		try {
			String msg=orderManagementService.createOrder(orderid);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/rest/flipkartordermanagement/submitorder/").buildAndExpand(orderid).toUri());
			return  ResponseEntity.status(HttpStatus.CREATED).body(msg);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(),e.getCause());
		}

	}
	

}
