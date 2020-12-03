package com.praveen.billing.model;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class BillingResponseData {
	@ApiModelProperty(required=true,example="true")
	private boolean executed;

}
