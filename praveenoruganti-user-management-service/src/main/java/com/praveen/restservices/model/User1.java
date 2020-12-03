package com.praveen.restservices.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//DROP TABLE IF EXISTS users1;
//CREATE TABLE users1(userId int NOT NULL DEFAULT '0',userName VARCHAR(100) NOT NULL,userEmail VARCHAR(100) DEFAULT NULL,address VARCHAR(100) DEFAULT NULL, PRIMARY KEY (userId ));
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User1  implements Serializable{
	private static final long serialVersionUID = 7096186377859686600L;
	private Integer userId;
	private String userName;
	private String userEmail;
	private String address;
}
