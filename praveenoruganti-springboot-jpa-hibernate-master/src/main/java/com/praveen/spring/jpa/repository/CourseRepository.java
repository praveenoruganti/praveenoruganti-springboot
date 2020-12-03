package com.praveen.spring.jpa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.praveen.spring.jpa.entity.Course;
import com.praveen.spring.jpa.entity.Review;

@Repository
@Transactional
public class CourseRepository {
	
	@Autowired
	EntityManager em;
	
	public Course findById(Long id) {		
		return em.find(Course.class, id);
	}
	
	public void deleteById(Long id) {
		Course course=findById(id);
		 em.remove(course);
	}
	
	public void save(Course course) {
		if(course.getId()==null) {
		  em.persist(course); //insert
		}else {
		  em.merge(course); //update
		}
	}
	
	public void addHardcodedReviewsForCourse() {
		Course course=findById(10004L);
		Review review1=new Review("Superb Course","FOUR");
		Review review2=new Review("Excellent Course","FIVE");
		course.addReview(review1);
		course.addReview(review2);
		review1.setCourse(course);
		review2.setCourse(course);
		em.persist(review1);
		em.persist(review2);
	}
	
	public void addReviewsForCourse(Long courseId,List<Review> reviews) {
		Course course=findById(courseId);
		for(Review review:reviews) {
			course.addReview(review);
			review.setCourse(course);
			em.persist(review);
		}
		
	}
}
