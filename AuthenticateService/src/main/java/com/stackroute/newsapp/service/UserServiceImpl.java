package com.stackroute.newsapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.newsapp.domain.User;
import com.stackroute.newsapp.exceptions.UserAlreadyExistsException;
import com.stackroute.newsapp.exceptions.UserNotFoundException;
import com.stackroute.newsapp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public boolean saveUser(User user) throws UserAlreadyExistsException {
		Optional<User> usr = userRepository.findById(user.getUserId());
		if (usr.isPresent())
			throw new UserAlreadyExistsException("User Already exists");
		userRepository.save(user);
		return true;
	}

	@Override
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
		User usr = userRepository.findByUserIdAndPassword(userId, password);
		if (usr == null)
			throw new UserNotFoundException("User Not Found");
		return usr;
	}

}
