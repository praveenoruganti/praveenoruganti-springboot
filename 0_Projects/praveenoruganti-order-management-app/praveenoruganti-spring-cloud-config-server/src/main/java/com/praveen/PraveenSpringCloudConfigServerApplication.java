package com.praveen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class PraveenSpringCloudConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PraveenSpringCloudConfigServerApplication.class, args);
	}

}
