package com.praveen.employee.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.praveen.employee.model.Employee;
import com.praveen.employee.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "EmployeeController", description = "Operations pertaining to employees in an organization")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@ApiOperation(value = "Get list of Employees in the Organizantion ", response = Iterable.class, tags = "getEmployees")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping(value = "/employee", produces = "application/json", consumes = "application/json")
	public List<Employee> getEmployees() {
		return employeeService.getEmployees();
	}

	@ApiOperation(value = "Create an Employee in the Organizantion ", response = Employee.class, tags = "createEmployee")
	@PostMapping("/employee")
	public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee emp, UriComponentsBuilder builder) {

		try {
			Employee employee = employeeService.createEmployee(emp);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/employee/{id}").buildAndExpand(emp.getEmpId()).toUri());
			// return new ResponseEntity<Employee>(headers, HttpStatus.CREATED);
			return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);

		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}

	}

	@ApiOperation(value = "Update an Employee in the Organizantion ", response = Employee.class, tags = "updateEmployee")
	@PostMapping("/employee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @Valid @RequestBody Employee emp,
			UriComponentsBuilder builder) {

		try {
			Employee employee = employeeService.updateEmployee(id, emp);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/employee/{id}").buildAndExpand(emp.getEmpId()).toUri());
			// return new ResponseEntity<Employee>(headers, HttpStatus.CREATED);
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);

		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}

	}

	@ApiOperation(value = "Get an Employee in the Organizantion ", response = Employee.class, tags = "getEmployee")
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id, UriComponentsBuilder builder) {
		try {
			Employee employee = employeeService.getEmployee(id);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/employee/{id}").buildAndExpand(employee.getEmpId()).toUri());
			// return new ResponseEntity<Employee>(headers, HttpStatus.CREATED);
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);

		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}

	@ApiOperation(value = "Delete an Employee in the Organizantion ", response = Employee.class, tags = "deleteEmployee")
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") Long id) {
		try {
		    employeeService.deleteEmployee(id);
		    return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
		   
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}

	@ResponseBody
	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	public String handleHttpMediaTypeNotAcceptableException() {
		return "acceptable MIME type:" + MediaType.APPLICATION_JSON_VALUE;
	}

}
