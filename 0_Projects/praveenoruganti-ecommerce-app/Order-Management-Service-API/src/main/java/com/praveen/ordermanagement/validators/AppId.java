package com.praveen.ordermanagement.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.PARAMETER,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=AppIdValidator.class)
@Documented
public @interface AppId {
	String message() default "App Id is not valid";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default { };

}
