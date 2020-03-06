package com.stackroute.newsapp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.stackroute.newsapp.domain.User;
import com.stackroute.newsapp.exceptions.UserAlreadyExistsException;
import com.stackroute.newsapp.service.UserService;
import com.stackroute.newsapp.tokengenerator.JwtTokenGenerator;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(path = "/user")
@EnableWebMvc
@Api(value = "newsapp", description = "News App")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtTokenGenerator jwtTokenGenerator;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		ResponseEntity<?> responseEntity;
		try {
			userService.saveUser(user);
			responseEntity = new ResponseEntity<String>("User Registration is Successful", HttpStatus.CREATED);
		} catch (UserAlreadyExistsException e) {
			responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return responseEntity;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) {
		ResponseEntity<?> responseEntity;
		User userInDb = null;
		String userId = user.getUserId();
		String password = user.getPassword();
		try {
			if (userId == null || password == null) {
				throw new Exception("userId/password is required");
			}

			userInDb = userService.findByUserIdAndPassword(userId, password);

			if (!userInDb.getPassword().equals(password)) {
				throw new Exception("password mismatch");
			}
			Map<String, String> tokenmap = jwtTokenGenerator.generateToken(userInDb);
			responseEntity = new ResponseEntity<Map<String, String>>(tokenmap, HttpStatus.OK);
		} catch (Exception e) {
			responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
			return responseEntity;
		}
		return responseEntity;
	}
}