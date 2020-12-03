package com.praveen.restservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.praveen.restservices.entities.User;

//Repository
@Repository
public interface UserJpaRepository  extends JpaRepository<User, Long>{

	User findByUsername(String username);
}
