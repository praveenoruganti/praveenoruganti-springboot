package com.praveen.spring.jpa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.praveen.spring.jpa.PraveenorugantiSpringbootJpaHibernateApplication;
import com.praveen.spring.jpa.entity.Course;
import com.praveen.spring.jpa.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=PraveenorugantiSpringbootJpaHibernateApplication.class)
public class JPQLTest {
	private Logger logger= LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public EntityManager em;
	
	@Test
	@DirtiesContext
	public void jpqlTest() {
		//Query q= em.createQuery("Select c from Course c");
		Query q= em.createNamedQuery("query_get_all_courses");
		logger.info("Select c from Course c ->  {}",q.getResultList()); 
		//Output: Select c from Course c ->  [Course[CORE JAVA], Course[SPRING], Course[JPA HIBERNATE], Course[SQL in 25 Days], Course[Maven in 25 Days]]		
	}
	
	@Test
	@DirtiesContext
	public void jpqltypedTest() {
		//TypedQuery<Course> q= em.createQuery("Select c from Course c",Course.class);
		
		TypedQuery<Course>  q= em.createNamedQuery("query_get_all_courses",Course.class);
		List<Course> qlist=q.getResultList();
		logger.info("Select c from Course c ->  {}",qlist);
		//Output: Select c from Course c ->  [Course[CORE JAVA], Course[SPRING], Course[JPA HIBERNATE], Course[SQL in 25 Days], Course[Maven in 25 Days]]		
	}
	
	@Test
	@DirtiesContext
	public void jpqlwhere() {
		//TypedQuery<Course> q= em.createQuery("Select c from Course c where name like '%25%' ",Course.class);
		TypedQuery<Course> q= em.createNamedQuery("query_get_like25",Course.class);
		List<Course> qlist=q.getResultList();
		logger.info("Select c from Course c where name like '%25%' ->  {}",qlist);
		//Output: Select c from Course c where name like '%25%' ->  [Course[SQL in 25 Days], Course[Maven in 25 Days]]
		
	}
	
	@Test
	public void jpql_courses_without_students() {
		TypedQuery<Course> query = em.createQuery("Select c from Course c where c.students is empty", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
		// [Course[Spring in 50 Steps]]
	}

	
	@Test
	public void jpql_courses_with_atleast_2_students() {
		TypedQuery<Course> query = em.createQuery("Select c from Course c where size(c.students) >= 2", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
		//[Course[JPA in 50 Steps]]
	}

	@Test
	public void jpql_courses_ordered_by_students() {
		TypedQuery<Course> query = em.createQuery("Select c from Course c order by size(c.students) desc", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
	}

	@Test
	public void jpql_students_with_passports_in_a_certain_pattern() {
		TypedQuery<Student> query = em.createQuery("Select s from Student s where s.passport.number like '%1234%'", Student.class);
		List<Student> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
	}

	//like
	//BETWEEN 100 and 1000
	//IS NULL
	//upper, lower, trim, length
	
	//JOIN => Select c, s from Course c JOIN c.students s
	//LEFT JOIN => Select c, s from Course c LEFT JOIN c.students s
	//CROSS JOIN => Select c, s from Course c, Student s
	//3 and 4 =>3 * 4 = 12 Rows
	@Test
	public void join(){
		Query query = em.createQuery("Select c, s from Course c JOIN c.students s");
		List<Object[]> resultList = query.getResultList();
		logger.info("Results Size -> {}", resultList.size());
		for(Object[] result:resultList){
			logger.info("Course{} Student{}", result[0], result[1]);
		}
	}

	@Test
	public void left_join(){
		Query query = em.createQuery("Select c, s from Course c LEFT JOIN c.students s");
		List<Object[]> resultList = query.getResultList();
		logger.info("Results Size -> {}", resultList.size());
		for(Object[] result:resultList){
			logger.info("Course{} Student{}", result[0], result[1]);
		}
	}

	@Test
	public void cross_join(){
		Query query = em.createQuery("Select c, s from Course c, Student s");
		List<Object[]> resultList = query.getResultList();
		logger.info("Results Size -> {}", resultList.size());
		for(Object[] result:resultList){
			logger.info("Course{} Student{}", result[0], result[1]);
		}
	}
	
	
}
