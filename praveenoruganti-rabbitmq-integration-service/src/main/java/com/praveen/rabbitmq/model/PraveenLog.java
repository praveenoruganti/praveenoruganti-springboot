package com.praveen.rabbitmq.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// CREATE TABLE PRAVEENLOG(PRAVEENLOG_ID int NOT NULL AUTO_INCREMENT, PRAVEENLOG_MESSAGE VARCHAR(10000), PRAVEENLOG_DATE DATE ,PRIMARY KEY (PRAVEENLOG_ID) )
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PraveenLog {
	
	private Integer logId;	
	private String logMessage;
	private Date logDate;

}
