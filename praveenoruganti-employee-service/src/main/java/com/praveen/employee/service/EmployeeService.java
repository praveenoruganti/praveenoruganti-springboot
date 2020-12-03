package com.praveen.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.praveen.employee.model.Employee;
import com.praveen.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

	public Employee createEmployee(Employee emp) throws Exception {
		if (employeeRepository.findByEmailIdOrMobile(emp.getEmailId(), emp.getMobile()) != null) {
			throw new Exception("Employee already available based on the Email Id or Mobile number");
		}
		return employeeRepository.save(emp);

	}

	public Employee updateEmployee(Long id, Employee emp) throws Exception {
		getEmployee(id);
		emp.setEmpId(id);
		return employeeRepository.save(emp);

	}

	public Employee getEmployee(Long id) throws Exception {	
		Optional<Employee> optEmployee=employeeRepository.findById(id);
		if (!optEmployee.isPresent()) {
			throw new Exception(" Employee Not Found");
		}
		
		return optEmployee.get();

	}
	
	public void deleteEmployee(Long id) throws Exception {
		getEmployee(id);
		employeeRepository.deleteById(id);
	}


}
