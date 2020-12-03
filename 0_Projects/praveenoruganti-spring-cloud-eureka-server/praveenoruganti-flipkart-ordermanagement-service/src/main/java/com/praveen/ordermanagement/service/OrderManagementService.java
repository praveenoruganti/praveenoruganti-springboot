package com.praveen.ordermanagement.service;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class OrderManagementService {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	@Value("${praveen-flipkart-ordermanagement-service.billingURL}")
	private String billingURL;

	@HystrixCommand(fallbackMethod = "callcreateOrder_Fallback",
			commandProperties = {
					//https://github.com/Netflix/Hystrix/wiki/Configuration
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),					
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value="5"),					
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value="50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value="5000")
    })			
	public String createOrder(String orderid) throws Exception {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<String> entity = new HttpEntity<String>(orderid, headers);
			// call billing service
			String msg = restTemplate.exchange(billingURL, HttpMethod.POST, entity, String.class).getBody();
			return msg;
		} catch (Exception e) {
			throw new Exception(e);
		}

	}

	@SuppressWarnings("unused")
	private String callcreateOrder_Fallback(String orderid) {
		System.out.println("Billing Service is down!!! fallback route enabled...");
		return "No Response From Billing Service at this moment. " + " Service will be back shortly - " + new Date();
	}

}
