package com.praveen.spring.jpa.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQueries(value = { @NamedQuery(name = "query_get_all_courses", query = "Select c from Course c"),
		@NamedQuery(name = "query_get_like25", query = "Select c from Course c where name like '%25%'") })
//@NamedQuery(name="query_get_all_courses",query="Select c from Course c")
@Cacheable
public class Course {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	@CreationTimestamp
	private LocalDateTime createdDate;

	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;

	//@JsonIgnore
	@OneToMany(mappedBy="course") // by default it is lazy fetch
	private List<Review> reviews = new ArrayList<Review>();
	
	@JsonIgnore
	@ManyToMany(mappedBy="courses") // by default it is lazy fetch
	private List<Student> students= new ArrayList<Student>();
	
	protected Course() {

	}

	public Course(String name) {
		this.name = name;
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

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public LocalDateTime getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void addReview(Review review) {
		this.reviews.add(review);
	}

	public void removeReview(Review review) {
		this.reviews.remove(review);
	}
	
	public List<Student> getStudents() {
		return students;
	}


	public void addStudent(Student student) {
		this.students.add(student);
	}

	public void removeStudent(Student student) {
		this.students.remove(student);
	}

	@Override
	public String toString() {
		return String.format("Course[%s]", name);
	}

}
