package com.praveen.billing.config;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import com.praveen.billing.model.CommonError;
import com.praveen.billing.model.ValidationError;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix="app")
@Data
@RefreshScope
public class ApplicationConfig {
	
	private List<String> appIds;
	
	private Map<String,List<ValidationError>> validationErrors;
	private CommonError clientError;
	private CommonError messageNotReadableError;
	private CommonError invalidHttpMethodError;
	private CommonError invalidInputError;
	private CommonError serverError;
	private CommonError serverErrors;
	

	

}
