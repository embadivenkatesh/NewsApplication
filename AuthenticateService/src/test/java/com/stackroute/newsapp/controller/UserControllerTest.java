package com.stackroute.newsapp.controller;

import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.newsapp.domain.User;
import com.stackroute.newsapp.service.UserService;
import com.stackroute.newsapp.tokengenerator.JwtTokenGenerator;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

	private transient MockMvc mockMvc;

	@MockBean
	private transient UserService service;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private JwtTokenGenerator tokenGenerator;

	private User user;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		user = new User("1", "first name", "lastname", "password", new Date());
	}

	@Test
	public void testRegisterUserSuccess() throws Exception {
		Mockito.when(service.saveUser(user)).thenReturn(true);
		mockMvc.perform(post("/user/register").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(convertJsonToString(user))).andExpect(status().isCreated());
		Mockito.verify(service, times(1)).saveUser(Mockito.any(User.class));
	}

	@Test
	public void testLoginUser() throws Exception {
		Mockito.when(service.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(user);
		mockMvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(convertJsonToString(user))).andExpect(status().isOk());
		Mockito.verify(service, times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());
	}

	public String convertJsonToString(User user) {
		ObjectMapper obj = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString = obj.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonString;
	}
}