package com.praveen.ordermanagement.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.praveen.ordermanagement.common.Constants;
import com.praveen.ordermanagement.model.OrderRequest;
import com.praveen.ordermanagement.model.OrderResponse;
import com.praveen.ordermanagement.model.OrderResponseData;

@Service
public class OrderManagementService {

	public OrderResponse createOrder(OrderRequest orderRequest) {

		return OrderResponse.builder().status(Constants.SUCCESS).orderId(orderRequest.getOrderId())
				.transactionId(orderRequest.getTransactionId()).appId(orderRequest.getAppId())
				.data(OrderResponseData.builder().message("Successfully placed the order")
						.totalPrice((orderRequest.getOrderList().stream().map(order -> order.getPrice())
								.reduce(BigDecimal.ZERO, BigDecimal::add)).setScale(2, BigDecimal.ROUND_HALF_UP))
						.build())
				.build();

	}

}
