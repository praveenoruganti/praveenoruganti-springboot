package com.praveen.restservices.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.praveen.restservices.entities.User;
import com.praveen.restservices.exceptions.UserExistsException;
import com.praveen.restservices.exceptions.UserNotFoundException;
import com.praveen.restservices.repository.UserRepository;

//Service
@Service
public class UserService {

	// Autowire the UserRepository
	@Autowired
	private UserRepository userRepository;

	// getAllUsers Method
	public List<User> getAllUsers() {

		return userRepository.findAll();

	}

	// CreateUser Method
	@Transactional(value=TxType.REQUIRED,rollbackOn=Exception.class)
	public void createUser(User user) throws UserExistsException{	
		userRepository.save(user);
	}

	// getUserById
	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		return Optional.of(userRepository.findById(id));
	}

	// updateUserById
	public void updateUserById(Long id, User user) throws UserNotFoundException {
		user.setUserid(id);
		userRepository.save(user);

	}

	// deleteUserById
	public void deleteUserById(Long id) {		
		userRepository.deleteById(id);
	}

}

