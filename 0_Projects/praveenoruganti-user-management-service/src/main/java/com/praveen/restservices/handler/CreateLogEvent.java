package com.praveen.restservices.handler;

import org.springframework.context.ApplicationEvent;

public class CreateLogEvent extends ApplicationEvent {

	private static final long serialVersionUID = 391259937537604095L;
	
	private String message;
	 
    public CreateLogEvent(Object source, String message) {
        super(source);
        this.message = message;
    }
    public String getMessage() {
        return message;
    }


}
