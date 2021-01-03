### Spring Security Jdbc Authentication

- Database Schema
The following table structure of the MySQL database is used to store user information and roles. As we are using JDBC, tables have to create manually.

	use securedb;

	CREATE TABLE `users` (
	  `username` VARCHAR(50) NOT NULL,
	  `password` VARCHAR(120) NOT NULL,
	  `enabled` TINYINT(1) NOT NULL,
	  PRIMARY KEY (`username`)
	);
	
	
	CREATE TABLE `authorities` (
	  `username` VARCHAR(50) NOT NULL,
	  `authority` VARCHAR(50) NOT NULL,
	  KEY `username` (`username`),
	  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`)
	  REFERENCES `users` (`username`)
	);


- Sample Json for creation of user

	JSON for ADMIN role
	{
		"userName":"praveen",
		"password":"password123",
		"roles":"ROLE_ADMIN"
	}
	JSON for USER role
	{
		"userName":"prasad",
		"password":"password",
		"roles":"ROLE_USER"
	}


- You can refer my [article](https://praveenorugantitech.blogspot.com/2019/05/spring-security-jdbc-authentication.html) for more details. 

### [Buy me a Book](https://www.buymeacoffee.com/praveenoruganti)