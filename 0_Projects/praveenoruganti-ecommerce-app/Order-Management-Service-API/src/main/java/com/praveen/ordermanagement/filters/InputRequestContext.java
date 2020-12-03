package com.praveen.ordermanagement.filters;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class InputRequestContext {
	private String appId;

	private String orderId;

	private String transactionId;
	
	private String httpMethodWithUriPath;
}
