package com.praveen.spring.jpa.compositekey;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "Phone")
@Table(name = "phone")
@Data
public class Phone {
	@Id
	@Column(name = "number")
	private String number;
	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "company_id", referencedColumnName = "company_id"),
			@JoinColumn(name = "employee_number", referencedColumnName = "employee_number") })
	private Employee1 employee1;
	
}
