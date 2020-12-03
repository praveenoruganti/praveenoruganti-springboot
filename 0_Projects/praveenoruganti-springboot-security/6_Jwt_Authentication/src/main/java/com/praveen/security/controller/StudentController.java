package com.praveen.security.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

	private static final List<Student> STUDENTS = Arrays.asList(Student.builder().Id(1).name("Praveen").build(),
			Student.builder().Id(2).name("Kiran").build(), Student.builder().Id(3).name("Praneeth").build());

	@GetMapping("")
	public List<Student> getAllStudents() {
		return STUDENTS;
	}

	@GetMapping("{id}")
	public Student getStudent(@PathVariable("id") Integer id) {
		return STUDENTS.stream().filter(student -> id.equals(student.getId())).findFirst()
				.orElseThrow(() -> new IllegalStateException("Student " + id + " does not exists"));
	}
}
