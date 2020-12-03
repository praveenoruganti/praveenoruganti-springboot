package com.praveen.employee.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@NoArgsConstructor
@Data
public class Employee implements Serializable{
	private static final long serialVersionUID = -9101411674332179398L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY,generator="empseq")
	@SequenceGenerator(name="empseq",sequenceName="EMP_SEQ") 
	@ApiModelProperty(notes = "The database generated Employee ID")
	private Long empId;
	@Column(length = 50, nullable = false)
	@ApiModelProperty(notes = "Name of the Employee should be in format flname",required=true,example="PraveenOruganti",position = 1)
	private String empName;
	@Column(length = 50, nullable = false)
	@ApiModelProperty(notes = "Email address must include @ and proper domain", example = "praveenoruganti@gmail.com", required = true)
	private String emailId;
	@Column(nullable = false)
	private String mobile;
	@Column(nullable = false)
	private Date hireDate;
	@Column(nullable = false)
	private String jobId;
	@Column(nullable = false)
	private Double empSalary;
	@Column(nullable = false)
	private Long departmentId;
	@Column(nullable = false)
	private Long managerId;
}
