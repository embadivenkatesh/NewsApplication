package com.stackroute.newsapp.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.stackroute.newsapp.domain.User;
import com.stackroute.newsapp.exceptions.UserAlreadyExistsException;
import com.stackroute.newsapp.exceptions.UserNotFoundException;
import com.stackroute.newsapp.repository.UserRepository;

public class UserServiceImplTest {

	@Mock
	private transient UserRepository userRepository;

	@InjectMocks
	private transient UserServiceImpl userServiceImpl;

	static transient User user;

	private transient Optional<User> opt;

	@Before
	public void setupmock() {
		MockitoAnnotations.initMocks(this);
		user = new User("1", "first name", "lastname", "password", new Date());
		opt = Optional.of(user);
	}

	@Test
	public void testSaveUserSuccess() throws UserAlreadyExistsException {
		Mockito.when(userRepository.save(user)).thenReturn(user);
		boolean isSaved = userServiceImpl.saveUser(user);
		assertTrue(isSaved);
		Mockito.verify(userRepository, times(1)).save(user);
		Mockito.verify(userRepository, times(1)).findById(user.getUserId());
	}

	@Test(expected = UserAlreadyExistsException.class)
	public void testSaveUserFailure() throws UserAlreadyExistsException {
		Mockito.when(userRepository.save(user)).thenReturn(user);
		Mockito.when(userRepository.findById(user.getUserId())).thenReturn(opt);
		boolean isSaved = userServiceImpl.saveUser(user);
		assertFalse("User failed to save", isSaved);
	}

	@Test
	public void testUserCredentialsSuccess() throws UserNotFoundException {
		Mockito.when(userRepository.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(user);
		User existingUser = userServiceImpl.findByUserIdAndPassword(user.getUserId(), user.getPassword());
		assertEquals("User is successfully logged", existingUser.getUserId(), user.getUserId());
		Mockito.verify(userRepository, times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());
	}

	@Test(expected = UserNotFoundException.class)
	public void testUserCredentialsfailure() throws UserNotFoundException {
		Mockito.when(userRepository.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(null);
		userServiceImpl.findByUserIdAndPassword(user.getUserId(), user.getPassword());
	}
}