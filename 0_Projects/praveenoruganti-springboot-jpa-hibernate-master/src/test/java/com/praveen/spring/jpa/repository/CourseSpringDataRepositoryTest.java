package com.praveen.spring.jpa.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.praveen.spring.jpa.PraveenorugantiSpringbootJpaHibernateApplication;
import com.praveen.spring.jpa.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=PraveenorugantiSpringbootJpaHibernateApplication.class)
public class CourseSpringDataRepositoryTest {
	private Logger log= LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public CourseSpringDataRepository courseSpringDataRepository;
	
	
	
	@Test
	@DirtiesContext
	public void findById_CoursePresent() {
		Optional<Course> courseOptional=courseSpringDataRepository.findById(10003L);
		assertTrue(courseOptional.isPresent());
	}
	
	@Test
	@DirtiesContext
	public void findByName() {
		log.info("FindByName -> {} ",courseSpringDataRepository.findByName("JPA HIBERNATE"));
	}
	
	@Test
	@DirtiesContext
	public void courseWith25DaysInName() {
		log.info("courseWith25DaysInName -> {} ",courseSpringDataRepository.courseWith25DaysInName());
	}
	
	@Test
	@DirtiesContext
	public void courseWith25DaysInNameNative() {
		log.info("courseWith25DaysInNameNative -> {} ",courseSpringDataRepository.courseWith25DaysInNameNative());
	}
	
	@Test
	@DirtiesContext
	public void search() {
		log.info("search for 25 Days -> {} ",courseSpringDataRepository.search("25 Days"));
	}
	
	
	@Test
	@DirtiesContext
	public void save() {
		log.info("save Test is running");
		Optional<Course> course=courseSpringDataRepository.findById(10003L);
		//assertEquals("JPA HIBERNATE", course.get().getName());
		course.get().setName("SQL");
		courseSpringDataRepository.save(course.get());
		assertEquals("SQL", course.get().getName());
		
	}
	@Test
	public void sort() {
		Sort sort= new Sort(Sort.Direction.DESC,"name");
		log.info("Sorted Courses -> {} ",courseSpringDataRepository.findAll(sort));
		log.info("Count -> {} ",courseSpringDataRepository.count());
	}
	
	@Test
	public void pagination() {
		PageRequest pageRequest= PageRequest.of(0, 2);
		Page<Course> firstPage= courseSpringDataRepository.findAll(pageRequest);
		log.info("First Page -> {} ",firstPage.getContent());
		
		Pageable secondPageable = firstPage.nextPageable();
		Page<Course> secondPage= courseSpringDataRepository.findAll(secondPageable);
		log.info("Second Page -> {} ",secondPage.getContent());
		
		Pageable thirdPageable = secondPage.nextPageable();
		Page<Course> thirdPage= courseSpringDataRepository.findAll(thirdPageable);
		log.info("Third Page -> {} ",thirdPage.getContent());
	}
	

}
