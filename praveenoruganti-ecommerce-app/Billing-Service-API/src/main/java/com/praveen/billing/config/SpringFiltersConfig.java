package com.praveen.billing.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import com.praveen.billing.filters.InputRequestFilter;

@Configuration
public class SpringFiltersConfig {

	@Bean
	public FilterRegistrationBean<InputRequestFilter> customerRequestFilter() {
		FilterRegistrationBean<InputRequestFilter> registrationBean = new FilterRegistrationBean<InputRequestFilter>();
		registrationBean.setFilter(new InputRequestFilter());
		registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		registrationBean.addUrlPatterns("/api/v1_0/*");
		return registrationBean;

	}
}
