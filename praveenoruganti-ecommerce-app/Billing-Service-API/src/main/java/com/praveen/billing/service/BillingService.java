package com.praveen.billing.service;

import org.springframework.stereotype.Service;

import com.praveen.billing.common.Constants;
import com.praveen.billing.model.BillingRequest;
import com.praveen.billing.model.BillingResponse;
import com.praveen.billing.model.BillingResponseData;

@Service
public class BillingService {

	public BillingResponse billingOrder(BillingRequest billingRequest) {
		return BillingResponse.builder().status(Constants.SUCCESS).orderId(billingRequest.getOrderId())
				.transactionId(billingRequest.getTransactionId()).appId(billingRequest.getAppId())
				.data(BillingResponseData.builder().executed(true).build()).build();
	}

}
