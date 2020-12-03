package com.praveen.billing.validators;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.praveen.billing.config.ApplicationConfig;

public class AppIdValidator implements ConstraintValidator<AppId,String>{
	
	private ApplicationConfig applicationConfig;
	
	@Inject
	public AppIdValidator( ApplicationConfig applicationConfig) {
		this.applicationConfig=applicationConfig;
	}
	
	@Override
	public boolean isValid(final String value, final ConstraintValidatorContext context) {
		return applicationConfig.getAppIds()!=null && applicationConfig.getAppIds().contains(value);
	}

}
