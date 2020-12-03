package com.praveen.ems.web.service;

import com.praveen.ems.web.model.User;
import com.praveen.ems.web.model.Login;

public interface UserService {

	void addUser(User user);

	User validateUser(Login login);

	User checkUser(Login login);
}