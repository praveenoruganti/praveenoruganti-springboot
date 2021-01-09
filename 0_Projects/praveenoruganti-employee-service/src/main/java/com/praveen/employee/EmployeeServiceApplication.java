package com.praveen.employee;

import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class EmployeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

	@Bean
	public Docket configDock() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(basePackage("com.praveen.employee.controller"))
				.paths(regex("/employee.*")).build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Employee Service Swagger").description("This is all about Employee service")
				.contact(new Contact("PRAVEEN ORUGANTI", "https://linktr.ee/praveenoruganti",
						"praveenorugantitech@gmail.com"))
				.license("Apache 2.0").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html").version("1.0.0")
				.build();

	}

}
