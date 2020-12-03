package com.praveen.restservices.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Size;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

//Entity 
//and
@ApiModel(description = "This model is to create a user")
@Entity
@Data
@RequiredArgsConstructor
@NamedQuery(name="find_all_users", query="Select u from User u") // JPQL: https://docs.jboss.org/hibernate/orm/4.3/devguide/en-US/html/ch11.html
public class User {

	@Id
	@GeneratedValue
	private Long userid;
	
	@ApiModelProperty(notes = "username should be in format flname", example = "PraveenOruganti", required = true, position = 1)
	@Size(min = 2, max = 50)
	@Column(name = "USER_NAME", length = 50, nullable = false, unique = true)
	public String username;

	@Size(min = 2, max = 50,  message = "FirstName should have atleast 2 characters")
	@Column(name = "FIRST_NAME", length = 50, nullable = false)
	public String firstname;

	@Column(name = "LAST_NAME", length = 50, nullable = false)
	public String lastname;
	
	@ApiModelProperty(notes = "Email address must include @ and proper domain", example = "op@gmail.com", required = true)
	@Column(name = "EMAIL_ADDRESS", length = 50, nullable = false)
	public String email;

	@Column(name = "ROLE", length = 50, nullable = false)
	public String role;

	@Column(name = "SSN", length = 50, nullable = false, unique = true)
	public String ssn;
	
	@Column(name = "ADDRESS")
	public String address;
	
	

}
