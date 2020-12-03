package com.praveen.restservices.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Configuration
@RefreshScope
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestDataValueProperties {

	@Value(value="${spring.application.name}")
	private String name;
	
}
