package com.praveen.ordermanagement.controller;

import static net.logstash.logback.argument.StructuredArguments.kv;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.praveen.ordermanagement.common.Constants;
import com.praveen.ordermanagement.model.OrderRequest;
import com.praveen.ordermanagement.model.OrderResponse;
import com.praveen.ordermanagement.service.OrderManagementService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class OrderManagementController {

	private OrderManagementService orderManagementService;

	public OrderManagementController(OrderManagementService orderManagementService) {
		this.orderManagementService = orderManagementService;
	}

	@ResponseStatus(value = HttpStatus.OK)
	@PostMapping(value = "/api/v1_0/submitorder", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create new order", tags = { "Create new order" })
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Input Request Validation Failures"),
		@ApiResponse(code = 404, message = "Not Found")})
	public OrderResponse createOrder(@Valid @RequestBody OrderRequest orderRequest) {
		
		log.info(Constants.INITAL_REQUEST, kv("request", orderRequest));
		OrderResponse orderResponse = orderManagementService.createOrder(orderRequest);
		log.info(Constants.FINAL_RESPONSE, kv("response", orderResponse));
		return orderResponse;

	}

}
