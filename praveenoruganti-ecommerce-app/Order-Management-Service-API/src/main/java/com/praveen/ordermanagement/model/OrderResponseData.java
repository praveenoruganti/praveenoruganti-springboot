package com.praveen.ordermanagement.model;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class OrderResponseData {
	@ApiModelProperty(required=true,example="")
	private String message;
	
	@ApiModelProperty(required=true,example="100.23")
	private BigDecimal totalPrice;

}

