package com.praveen.billing.model;


import java.util.Map;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@ApiModel("Error Details to describe the Client, Business and System Errors")
public class ErrorMessage {
	
	@NotNull
	@ApiModelProperty(required= true,notes="${apidocs.ErrorMessage.code.notes}",example= "4000")
	private String code;
	
	@NotNull
	@ApiModelProperty(required= true,notes="${apidocs.ErrorMessage.description.notes}",example= "Input Request Validation Failure")
	private String description;

	@NotNull
	@ApiModelProperty(required= true,notes="${apidocs.ErrorMessage.code.notes}",example= "{\"400001\":\"Request Json is malformed or invalid\"}")
	private Map<String,String> errors;

}
