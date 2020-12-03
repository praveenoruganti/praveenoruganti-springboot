package com.praveen.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import zipkin.server.EnableZipkinServer;


@SpringBootApplication
@EnableZipkinServer
@EnableDiscoveryClient
public class PraveenZipkinServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PraveenZipkinServerApplication.class, args);
	}

}
