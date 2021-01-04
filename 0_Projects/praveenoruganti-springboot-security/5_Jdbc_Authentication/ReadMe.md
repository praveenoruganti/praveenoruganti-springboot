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

### [Buy me a Book](https://bit.ly/388sUbE)


### Connect with me:

[<img align="left" alt="praveenorugantitech.blogspot.com" width="22px" src="https://raw.githubusercontent.com/iconic/open-iconic/master/svg/globe.svg" />][website]
[<img align="left" alt="praveenoruganti | Facebook Group" width="22px" src="https://cdn.jsdelivr.net/npm/simple-icons@v3/icons/facebook.svg" />][facebookgroup]
[<img align="left" alt="praveenoruganti | Twitter" width="22px" src="https://cdn.jsdelivr.net/npm/simple-icons@v3/icons/twitter.svg" />][twitter]
[<img align="left" alt="praveenoruganti | Instagram" width="22px" src="https://cdn.jsdelivr.net/npm/simple-icons@v3/icons/instagram.svg" />][instagram]
[<img align="left" alt="praveenoruganti | Email" width="22px" src="https://cdn.jsdelivr.net/npm/simple-icons@v3/icons/gmail.svg" />][email]

<br/>

[website]: https://praveenorugantitech.blogspot.com
[twitter]: https://mobile.twitter.com/praveenoruganti
[facebookgroup]: https://www.facebook.com/groups/praveenorugantitech
[instagram]: https://instagram.com/praveenorugantitech
[email]: mailto:praveenorugantitech@gmail.com
