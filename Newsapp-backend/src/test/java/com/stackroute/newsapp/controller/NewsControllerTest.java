package com.stackroute.newsapp.controller;

import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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
import com.stackroute.newsapp.domain.News;
import com.stackroute.newsapp.service.NewsService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = NewsController.class)
public class NewsControllerTest {

	private transient MockMvc mockMvc;

	@MockBean
	private transient NewsService service;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private transient News news;

	static List<News> newsList;

	@Before
	public void setup() {

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		newsList = new ArrayList<>();
		news = new News(1, "venkatesh", "good news", "www.bbc.com", "wow", "google.com", "123", "526645");
		newsList.add(news);
		news = new News(2, "embadi", "sports news", "www.bbc.com", "wow", "google.com", "123", "526645");
		newsList.add(news);
	}

	@Test
	public void testSaveNewsSuccess() throws Exception {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1MjY2NDUiLCJpYXQiOjE1NTM5MzU0OTZ9.o-YCaDZFJNXYnVA7_d6UcX4LJXF8evRJPyHtFhE5bS4";
		Mockito.when(service.saveNews(news)).thenReturn(true);
		mockMvc.perform(post("/api/news").header("authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON).content(convertJsonToString(news)))
				.andExpect(status().isCreated());
		Mockito.verify(service, times(1)).saveNews(Mockito.any(News.class));

	}

	@Test
	public void testGetNewsByUrlSuccess() throws Exception {
		Mockito.when(service.getNewsByUrl(news.getUrl())).thenReturn(news);
		mockMvc.perform(get("/api/news/{url}", news.getUrl()).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		Mockito.verify(service, times(1)).getNewsByUrl(news.getUrl());

	}

	@Test
	public void testDeleteNewsByUrlSuccess() throws Exception {
		Mockito.when(service.deleteNewsById(news.getId())).thenReturn(true);
		mockMvc.perform(delete("/api/news/{id}", news.getId()).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		Mockito.verify(service, times(1)).deleteNewsById(news.getId());

	}

	@Test
	public void testGetAllNewsSuccess() throws Exception {
		Mockito.when(service.getAllNews()).thenReturn(newsList);
		mockMvc.perform(get("/api/news").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		Mockito.verify(service, times(1)).getAllNews();

	}

	@Test
	public void testGetMyNews() throws Exception {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1MjY2NDUiLCJpYXQiOjE1NTM5MzU0OTZ9.o-YCaDZFJNXYnVA7_d6UcX4LJXF8evRJPyHtFhE5bS4";
		Mockito.when(service.getMyNews("526645")).thenReturn(newsList);
		mockMvc.perform(get("/api/news/mynews").header("authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		Mockito.verify(service, times(1)).getMyNews("526645");

	}

	public String convertJsonToString(News news) {
		ObjectMapper obj = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString = obj.writeValueAsString(news);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonString;

	}

}
