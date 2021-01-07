package com.praveen.spring.jpa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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

@RunWith(SpringRunner.class)
@SpringBootTest(classes=PraveenorugantiSpringbootJpaHibernateApplication.class)
public class NativeQueryTest {
	private Logger log= LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public EntityManager em;
	
	@Test
	@DirtiesContext
	public void nativequeryTest() {
		Query q= em.createNativeQuery("select * from Course",Course.class);
		log.info("select * from Course ->  {}",q.getResultList()); 
		//Output: select * from Course ->  [Course[CORE JAVA], Course[SPRING], Course[JPA HIBERNATE], Course[SQL in 25 Days], Course[Maven in 25 Days]]		
	}
	
		
	@Test
	@DirtiesContext
	public void nativequerywhere() {
		Query q= em.createNativeQuery("select * from Course where name like '%25%'",Course.class);
		log.info("Select c from Course c where name like '%25%' ->  {}",q.getResultList());
		//Output: Select c from Course c where name like '%25%' ->  [Course[SQL in 25 Days], Course[Maven in 25 Days]]
		
	}
	
	@Test
	@Transactional
	public void native_queries_to_update() {
		Query query = em.createNativeQuery("Update COURSE set last_updated_date=sysdate()");
		int noOfRowsUpdated = query.executeUpdate();
		log.info("noOfRowsUpdated  -> {}", noOfRowsUpdated);
	}
	
	
	
	@Test
	public void native_queries_with_parameter() {
		Query query = em.createNativeQuery("SELECT * FROM COURSE where id = ?", Course.class);
		query.setParameter(1, 10001L);
		List resultList = query.getResultList();
		log.info("SELECT * FROM COURSE  where id = ? -> {}", resultList);
	}

	@Test
	public void native_queries_with_named_parameter() {
		Query query = em.createNativeQuery("SELECT * FROM COURSE where id = :id", Course.class);
		query.setParameter("id", 10001L);
		List resultList = query.getResultList();
		log.info("SELECT * FROM COURSE  where id = :id -> {}", resultList);
	}
	
}
