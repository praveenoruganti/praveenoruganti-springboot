package com.praveen.springboot.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@EnableAdminServer
@SpringBootApplication
@EnableEurekaClient
public class SpringbootAdminServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAdminServerApplication.class, args);
	}

}
