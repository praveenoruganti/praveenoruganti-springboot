package com.praveen.spring.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.praveen.spring.jpa.entity.Course;

@RepositoryRestResource(path="courses")
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {
	List<Course> findByName(String name);

	List<Course> deleteByName(String name);

	@Query("Select c from Course c Where c.name like '%25 Days%'")
	List<Course> courseWith25DaysInName();

	@Query(value = "Select * from Course c Where c.name like '%25 Days%'", nativeQuery = true)
	List<Course> courseWith25DaysInNameNative();

	@Query(value = "SELECT c from Course c where c.name LIKE '%' || :keyword || '%'")
	List<Course> search(@Param("keyword") String keyword);
}
