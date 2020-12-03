package com.praveen.spring.jpa.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.praveen.spring.jpa.PraveenorugantiSpringbootJpaHibernateApplication;
import com.praveen.spring.jpa.entity.Passport;
import com.praveen.spring.jpa.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=PraveenorugantiSpringbootJpaHibernateApplication.class)
public class StudentRepositoryTest {
	private Logger log= LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public StudentRepository studentRepository;
	
	@Autowired
	public EntityManager em;
	
	
	@Test
	@Transactional
	public void retreiveStudentWithPassportDetails() {
		Student student= em.find(Student.class, 20001L);
		log.info("student -> {}",student);
		log.info("passport -> {}",student.getPassport());
		
		Passport passport = em.find(Passport.class,40001L);
		log.info("passport -> {}",passport);
		log.info("student -> {}",passport.getStudent());
		
	}
	

	@Test
	@Transactional
	public void retrievePassportAndAssociatedStudent() {
		Passport passport = em.find(Passport.class, 40001L);
		log.info("passport -> {}", passport);
		log.info("student -> {}", passport.getStudent());
	}
	
	@Test
	@Transactional
	public void retrieveStudentAndCourses() {
		Student student = em.find(Student.class, 20001L);
		
		log.info("student -> {}", student);
		log.info("courses -> {}", student.getCourses());
	}
	

}
