package com.praveen.billing.controller;

import static net.logstash.logback.argument.StructuredArguments.kv;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.praveen.billing.common.Constants;
import com.praveen.billing.model.BillingRequest;
import com.praveen.billing.model.BillingResponse;
import com.praveen.billing.service.BillingService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BillingController {

	private BillingService billingService;

	public BillingController(BillingService billingService) {
		this.billingService = billingService;
	}

	@ResponseStatus(value = HttpStatus.OK)
	@PostMapping(value = "/api/v1_0/billingorder", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Process Billing Order", tags = { "Process Billing Order" })
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Input Request Validation Failures"),
			@ApiResponse(code = 404, message = "Not Found")})
	public BillingResponse processBillingOrder(@Valid @RequestBody BillingRequest billingRequest) {
		log.info(Constants.INITAL_REQUEST, kv("request", billingRequest));
		BillingResponse billingResponse = billingService.billingOrder(billingRequest);
		log.info(Constants.FINAL_RESPONSE, kv("response", billingResponse));
		return billingResponse;

	}

}
