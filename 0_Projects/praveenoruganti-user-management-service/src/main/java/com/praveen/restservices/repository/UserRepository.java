package com.praveen.restservices.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.praveen.restservices.entities.User;

@Repository
public class UserRepository {
	
	@PersistenceContext
	EntityManager entityManager;

	@Transactional
	public User findById(Long id) {	
		Optional<User> optionalUser = Optional.of(entityManager.find(User.class, id));
		if (!optionalUser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User Not found in user Repository, provide the correct user id");
		}
		return optionalUser.get();
	}

	@Transactional
	public void save(User user) {	
		if(user.getUserid() ==null) {
			entityManager.persist(user);
		}else {
			entityManager.merge(user);
		}
		
	}

	@Transactional
	public void deleteById(Long id) {
		Optional<User> optionalUser = Optional.of(findById(id));		
		entityManager.remove(optionalUser.get());
	}
	
	@Transactional
	public List<User> findAll() {		
		return entityManager.createNamedQuery("find_all_users",User.class).getResultList();
	}

	

	
}
