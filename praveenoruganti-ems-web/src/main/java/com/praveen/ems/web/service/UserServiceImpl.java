package com.praveen.ems.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.praveen.ems.web.model.User;
import com.praveen.ems.web.model.Login;
import com.praveen.ems.web.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	public UserRepository userRepo;

	public void addUser(User user) {
		userRepo.save(user);
	}

	public User validateUser(Login login) {
		return userRepo.findByUsernameAndPassword(login.getUsername(), login.getPassword());
	}
	
	public User checkUser(Login login) {
		return userRepo.findByUsername(login.getUsername());
	}

}
