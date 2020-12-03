package com.praveen.spring.jpa.repository;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

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
import com.praveen.spring.jpa.entity.Review;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=PraveenorugantiSpringbootJpaHibernateApplication.class)
public class CourseRepositoryTest {
	private Logger log= LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public CourseRepository courseRepository;
	
	@Autowired
	public EntityManager em;
	
	
	@Test
	@DirtiesContext
	public void findById() {
		log.info("findById Test is running");
		Course course=courseRepository.findById(10003L);
		assertEquals("JPA HIBERNATE", course.getName());
	}
	
	@Test
	@DirtiesContext
	public void save() {
		log.info("save Test is running");
		Course course=courseRepository.findById(10003L);
		assertEquals("JPA HIBERNATE", course.getName());
		course.setName("SQL");
		courseRepository.save(course);
		assertEquals("SQL", course.getName());
		
	}
	
	@Test
	@Transactional
	public void retrieveReviewsForCourse() {
		Course course=courseRepository.findById(10003L);
		log.info("{}",course.getReviews());
	}
	
	@Test
	@Transactional
	public void retrieveCourseForReview() {
		Review review=em.find(Review.class,50001L);
		log.info("{}",review.getCourse());
	}

}
