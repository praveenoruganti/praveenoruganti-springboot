package com.praveen.spring.jpa.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.praveen.spring.jpa.entity.Course;
import com.praveen.spring.jpa.entity.Passport;
import com.praveen.spring.jpa.entity.Student;

@Repository
@Transactional
public class StudentRepository {
	
	@Autowired
	EntityManager em;
	
	public Student findById(Long id) {		
		return em.find(Student.class, id);
	}
	
	public void deleteById(Long id) {
		Student student=findById(id);
		 em.remove(student);
	}
	
	public void save(Student student) {
		if(student.getId()==null) {
		  em.persist(student); //insert
		}else {
		  em.merge(student); //update
		}
	}
	
	public void saveStudentWithPassport() {
		Passport passport= new Passport("Z123456");
		em.persist(passport);
		Student student= new Student("Varma");
		student.setPassport(passport);
		em.persist(student);
	}
	
	public void someOperationToUnderstandPersistenceContext() {
		//Database Operation 1 - Retrieve student
		Student student = em.find(Student.class, 20001L);
		//Persistence Context (student)
		
		
		//Database Operation 2 - Retrieve passport
		Passport passport = student.getPassport();
		//Persistence Context (student, passport)

		//Database Operation 3 - update passport
		passport.setNumber("E123457");
		//Persistence Context (student, passport++)
		
		//Database Operation 4 - update student
		student.setName("Ranga - updated");
		//Persistence Context (student++ , passport++)
	}
	
	public void insertHardcodedStudentAndCourse(){
		Student student = new Student("Naveen");
		Course course = new Course("JPA HIBERNATE");
		em.persist(student);
		em.persist(course);
		
		student.addCourse(course);
		course.addStudent(student);
		em.persist(student);
	}

	public void insertStudentAndCourse(Student student, Course course){
		student.addCourse(course);
		course.addStudent(student);

		em.persist(student);
		em.persist(course);
	}
}
