package com.praveen.ems.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@Table(name = "USER")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID", nullable = false)
	private int userid;
	@Size(min = 2, max = 50)
	@NotBlank(message = "User name is Mandatory field. Please provide user name")
	@Column(name = "USER_NAME", length = 50, nullable = false, unique = true)
	private String username;
	@NotBlank(message = "Password is Mandatory field. Please provide user name")
	@Column(name = "USER_PASS", length = 30,nullable = false)
	private String password;

}