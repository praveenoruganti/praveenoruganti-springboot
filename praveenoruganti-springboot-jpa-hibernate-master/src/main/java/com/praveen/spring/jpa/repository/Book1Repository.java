package com.praveen.spring.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.praveen.spring.jpa.entity.Book1;

@Repository
public interface Book1Repository extends JpaRepository<Book1, Long>, Book1RepositoryCustom   {
}
