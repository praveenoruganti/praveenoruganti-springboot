package com.praveen.spring.jpa.compositekey;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "Employee1")
@Table(name = "employee1")
@Data
public class Employee1 {
	@EmbeddedId
	private EmployeeId id;
	private String name;
}
