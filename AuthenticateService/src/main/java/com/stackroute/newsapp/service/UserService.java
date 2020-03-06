package com.stackroute.newsapp.service;

import com.stackroute.newsapp.domain.User;
import com.stackroute.newsapp.exceptions.UserAlreadyExistsException;
import com.stackroute.newsapp.exceptions.UserNotFoundException;

public interface UserService {
	boolean saveUser(User user) throws UserAlreadyExistsException;
	User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException;
}
