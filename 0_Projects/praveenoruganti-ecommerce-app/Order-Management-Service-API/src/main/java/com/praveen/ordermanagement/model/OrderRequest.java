package com.praveen.ordermanagement.model;

import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.praveen.ordermanagement.validators.AppId;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class OrderRequest {

	@AppId
	@Size(min = 0, max = 20)
	@ApiModelProperty(required = true, position = 1, example = "OrderManagement")
	private String appId;
	@ApiModelProperty(required = true, position = 2, example = "100")
	@NotNull
	@Digits(fraction = 0, integer = 25)
	private String orderId;	
	@NotEmpty
	@Size(min = 0, max = 20)
	@ApiModelProperty(required = true, position = 3, example = "test_123")
	private String transactionId;	
	@NotEmpty
	@ApiModelProperty(required = true, position = 4)
	private List<Order> orderList;

}
