package com.praveen.billing.service;

import javax.validation.Valid;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
@Service
public class BillingService {
	Logger log = LoggerFactory.getLogger(BillingService.class);

	public String billingOrder(@Valid String orderid) throws Exception {
		int orderidNum=Integer.parseInt(orderid);
		// This logic is just used for testing the service and will not be used in real time.
		if(orderidNum<=0) {
			log.info(orderid);
			throw new Exception("Invalid Order ID " + orderid);
		}else if(orderidNum>0 && orderidNum<500) {
			log.info(orderid);
			throw new Exception("Payment failed for Order ID "+orderid);
		}else {
			log.info(orderid);
			return "Successfully placed the order "+orderid;
		}
		
	}

}
