package com.praveen.restservices.dao;

import java.util.List;

import com.praveen.restservices.model.User1;

public interface UserDAO1 {
	abstract List<User1> findAll1() throws Exception;
	abstract User1 findUserById1(String userid1) throws Exception;
	abstract int create1(final User1 user1) throws Exception;
	abstract void deleteByUserId1(final String userId1) throws Exception;
	abstract int updateUserbyId1(User1 user1) throws Exception;

}
