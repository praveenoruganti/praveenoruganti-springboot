package com.praveen.billing.controller;

import static net.logstash.logback.argument.StructuredArguments.kv;

import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringSubstitutor;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.google.common.collect.Maps;
import com.praveen.billing.common.Constants;
import com.praveen.billing.config.ApplicationConfig;
import com.praveen.billing.filters.InputRequestContextHolder;
import com.praveen.billing.model.BillingResponse;
import com.praveen.billing.model.ErrorMessage;
import com.praveen.billing.model.ValidationError;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

	private ApplicationConfig applicationConfig;

	public ExceptionControllerAdvice(ApplicationConfig applicationConfig) {
		this.applicationConfig = applicationConfig;
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<BillingResponse> handleHttpMessageNotReadableException() {
		Map<String, String> validationErrors = Maps.newHashMap();

		validationErrors.put(applicationConfig.getMessageNotReadableError().getCode(),
				applicationConfig.getMessageNotReadableError().getDescription());
		BillingResponse billingResponse = BillingResponse.builder().status(Constants.FAIL).orderId(InputRequestContextHolder.getOrderId()).transactionId(InputRequestContextHolder.getTransactionId())
				.appId(InputRequestContextHolder.getAppId())
				.errors(ErrorMessage.builder().code(applicationConfig.getClientError().getCode())
						.description(applicationConfig.getClientError().getDescription()).errors(validationErrors)
						.build())
				.build();

		return errorResponse(HttpStatus.BAD_REQUEST, billingResponse);

	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<BillingResponse> handleValidationErrors(
			ConstraintViolationException constraintViolationException) {
		Map<String, String> validationErrors = Maps.newHashMap();
		constraintViolationException.getConstraintViolations().forEach(violationErrors -> {
			String field = ((PathImpl) violationErrors.getPropertyPath()).getLeafNode().asString().trim();
			List<ValidationError> validationErrorList = applicationConfig.getValidationErrors().get(field.trim());
			validationErrorList.forEach(validationError -> validationErrors.put(validationError.getCode(),
					replaceTemplateValues(validationError.getDescription(), field.trim())));
		});
		BillingResponse billingResponse = BillingResponse.builder().status(Constants.FAIL).orderId(InputRequestContextHolder.getOrderId()).transactionId(InputRequestContextHolder.getTransactionId())
				.appId(InputRequestContextHolder.getAppId())
				.errors(ErrorMessage.builder().code(applicationConfig.getClientError().getCode())
						.description(applicationConfig.getClientError().getDescription()).errors(validationErrors)
						.build())
				.build();

		log.error(Constants.INPUT_VALIDATION_ERROR, kv(Constants.ERROR_RESPONSE, billingResponse),
				constraintViolationException);

		return errorResponse(HttpStatus.BAD_REQUEST, billingResponse);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<BillingResponse> handleClientErrors(
			MethodArgumentNotValidException methodArgumentNotValidException) {
		Map<String, String> validationErrors = Maps.newHashMap();
		if (methodArgumentNotValidException.getBindingResult().getGlobalError() != null) {
			populateGlobalError(validationErrors, methodArgumentNotValidException.getBindingResult().getGlobalError());
		}
		methodArgumentNotValidException.getBindingResult().getFieldErrors().stream()
				.forEach(fieldError -> populateValidationErrors(validationErrors, fieldError));
		BillingResponse billingResponse = BillingResponse.builder().status(Constants.FAIL).orderId(InputRequestContextHolder.getOrderId()).transactionId(InputRequestContextHolder.getTransactionId())
				.appId(InputRequestContextHolder.getAppId())
				.errors(ErrorMessage.builder().code(applicationConfig.getClientError().getCode())
						.description(applicationConfig.getClientError().getDescription()).errors(validationErrors)
						.build())
				.build();

		log.error(Constants.INPUT_VALIDATION_ERROR, kv(Constants.ERROR_RESPONSE, billingResponse),
				methodArgumentNotValidException);

		return errorResponse(HttpStatus.BAD_REQUEST, billingResponse);
	}

	private void populateValidationErrors(Map<String, String> validationErrors, FieldError fieldError) {
		String fieldName = deriveFieldName(fieldError);
		List<ValidationError> validationErrorsList = applicationConfig.getValidationErrors().get(fieldName);
		validationErrorsList.forEach(validationError -> validationErrors.put(validationError.getCode(),
				replaceTemplateValues(validationError.getDescription(), fieldName)));
	}

	private String deriveFieldName(FieldError fieldError) {
		String fieldName = fieldError.getField();
		if (fieldName.contains(".")) {
			fieldName = StringUtils.substringAfter(fieldName, ".");
		}
		return fieldName;
	}

	private void populateGlobalError(Map<String, String> validationErrors, ObjectError fieldError) {

		List<ValidationError> validationErrorsList = applicationConfig.getValidationErrors()
				.get(fieldError.getDefaultMessage());
		validationErrorsList.forEach(validationError -> validationErrors.put(validationError.getCode(),
				replaceTemplateValues(validationError.getDescription(), fieldError.getDefaultMessage())));

	}

	private String replaceTemplateValues(String templateMessage, String fieldName) {
		Map<String, String> validationOverrides = Maps.newHashMap();
		if (StringUtils.equalsIgnoreCase("appId", fieldName)) {
			validationOverrides.put("appIds", StringUtils.join(applicationConfig.getAppIds()));
		}
		StringSubstitutor sub = new StringSubstitutor(validationOverrides);
		return sub.replace(templateMessage);
	}

	private ResponseEntity<BillingResponse> errorResponse(HttpStatus status, BillingResponse billingResponse) {
		return ResponseEntity.status(status).body(billingResponse);
	}

}
