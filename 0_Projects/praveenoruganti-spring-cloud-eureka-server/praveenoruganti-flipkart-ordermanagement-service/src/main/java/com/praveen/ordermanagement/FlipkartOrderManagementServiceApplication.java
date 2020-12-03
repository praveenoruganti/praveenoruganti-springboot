package com.praveen.ordermanagement;

import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableEurekaClient
@EnableSwagger2
@EnableHystrixDashboard
@EnableCircuitBreaker
//@RibbonClient(name = "billingservice", configuration = RibbonConfiguration.class)
@SpringBootApplication
@EnableFeignClients
public class FlipkartOrderManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlipkartOrderManagementServiceApplication.class, args);
	}

	@Bean
	@LoadBalanced
	   public RestTemplate getRestTemplate() {
//		HttpComponentsClientHttpRequestFactory factory =new  HttpComponentsClientHttpRequestFactory();
//		factory.setConnectTimeout(3000);
//	      return new RestTemplate(factory);
		return new RestTemplate();
	   }

	@Bean
	public Docket configDock() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(basePackage("com.praveen.ordermanagement.controller"))
				.paths(regex("/rest.*")).build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("FlipkartOrderManagementService")
				.description("WELCOME TO SWAGGER CLIENT")
				.contact(new Contact("PRAVEEN ORUGANTI", "https://praveenorugantitech.blogspot.com/", "praveenorugantitech@gmail.com"))
				.license("Apache 2.0").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html").version("1.0.0")
				.build();
	}

}
