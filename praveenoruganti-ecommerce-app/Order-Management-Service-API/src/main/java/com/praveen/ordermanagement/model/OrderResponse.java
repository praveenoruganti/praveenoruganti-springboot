package com.praveen.ordermanagement.model;



import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class OrderResponse {
	@ApiModelProperty(required=true,position=1,example="Success")
	private String status;
	@ApiModelProperty(required = true,position=2,example="100")
	private String orderId;
	@ApiModelProperty(required = true,position=3,example="test_123")
	private String transactionId;
	@ApiModelProperty(required = true,position=4,example="OrderManagement")
	private String appId;	
	@ApiModelProperty(required = true,position=5)
	private OrderResponseData data;
	private ErrorMessage errors;
}
