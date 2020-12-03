package com.praveen.spring.jpa.compositekey;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class EmployeeId implements Serializable {
	private static final long serialVersionUID = 5433558671941980196L;
	
	@Column(name = "company_id")
	private Long companyId;
	@Column(name = "employee_number")
	private Long employeeNumber;
}
