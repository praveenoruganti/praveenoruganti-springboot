package com.praveen.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.praveen.employee.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	public Employee findByEmailIdOrMobile(String emailId,String mobile);

}
