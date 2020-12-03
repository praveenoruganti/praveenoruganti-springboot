package com.praveen.security.student;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {
	
	

	private static List<Student> STUDENTS = new ArrayList<Student>(Arrays.asList(
			Student.builder().id(1).name("praveenoruganti").build(),
			Student.builder().id(2).name("kirankumar").build(),
			Student.builder().id(3).name("praneeth").build()));

    @GetMapping("{id}")
		public Student getStudent(@PathVariable("id") Integer id) {
			return STUDENTS.stream().filter(student -> id.equals(student.getId())).findFirst()
					.orElseThrow(() -> new IllegalStateException("Student " + id + " does not exists"));
	}

	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
	public List<Student> getAllStudents() {
		System.out.println("getAllStudents");
		return STUDENTS;
	}

	@PostMapping("/register")
	@PreAuthorize("hasAuthority('student:write')")
	public void registerNewStudent(@RequestBody Student student) {
		STUDENTS.add(student);
		System.out.println("registerNewStudent");
		System.out.println(student);
	}

	@DeleteMapping(path = "{id}")
	@PreAuthorize("hasAuthority('student:write')")
	public void deleteStudent(@PathVariable("id") Integer id) {
		System.out.println("deleteStudent");
		STUDENTS.removeIf(student -> student.getId().intValue() == id.intValue());
		System.out.println(id);
	}

	@PutMapping(path = "{id}")
	@PreAuthorize("hasAuthority('student:write')")
	public void updateStudent(@PathVariable("id") Integer id, @RequestBody Student student) {		
		System.out.println("updateStudent");
		for (Student studentUpdated : STUDENTS) {
		    if (studentUpdated.getId().equals(id)) {
		    	studentUpdated.setName(student.getName());
		        break;  
		    }
		}
		System.out.println(String.format("%s %s", id, student));
	}
}
