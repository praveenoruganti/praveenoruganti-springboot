package com.praveen.restservices.handler;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CreateLogHandler implements ApplicationListener<CreateLogEvent> {

	@Override
	public void onApplicationEvent(CreateLogEvent event) {
		System.out.println("Received Create Log Event  " + event.getMessage());
		
	}

}
