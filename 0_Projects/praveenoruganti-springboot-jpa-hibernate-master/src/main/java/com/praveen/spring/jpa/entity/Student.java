package com.praveen.spring.jpa.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;


@Entity
public class Student {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private String name;	
	 
	@OneToOne(fetch=FetchType.LAZY) // by default it is eager fetch
	private Passport passport; // SELECT * FROM STUDENT,PASSPORT WHERE STUDENT.PASSPORT_ID= PASSPORT.ID

	@ManyToMany // by default it  is lazy fetch
	@JoinTable(name="STUDENT_COURSE",	
	joinColumns=	@JoinColumn(name="STUDENT_ID"),
	inverseJoinColumns=@JoinColumn(name="COURSE_ID"))
	public List<Course> courses= new ArrayList<Course>();
	
	protected Student() {
		
	}
	public Student(String name){
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}
	
	public Passport getPassport() {
		return passport;
	}
	public void setPassport(Passport passport) {
		this.passport = passport;
	}
	public List<Course> getCourses() {
		return courses;
	}
	
	public void addCourse(Course course) {
		this.courses.add(course);
	}

	public void removeCourse(Course course) {
		this.courses.remove(course);
	}
	@Override
	public String toString() {
		return String.format("Student[%s]", name);
	}
	
}
