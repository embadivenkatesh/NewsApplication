package com.stackroute.newsapp.controller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.newsapp.domain.News;
import com.stackroute.newsapp.exceptions.NewsAlreadyExistsException;
import com.stackroute.newsapp.exceptions.NewsNotFoundException;
import com.stackroute.newsapp.service.NewsService;

import io.jsonwebtoken.Jwts;
import io.swagger.annotations.Api;

@RestController
@RequestMapping(path = "/api/news")
@Api(value = "newsapp", description = "News App")
@CrossOrigin
public class NewsController {

	@Autowired
	private NewsService newsService;

	@PostMapping
	public ResponseEntity<?> saveNews(@RequestBody News news, HttpServletRequest req) {
		ResponseEntity<?> responseEntity;
		String authHeader = req.getHeader("authorization");
		String token = authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("secretKey").parseClaimsJws(token).getBody().getSubject();
		try {
			news.setUserId(userId);
			newsService.saveNews(news);
			responseEntity = new ResponseEntity<News>(news, HttpStatus.CREATED);
		} catch (NewsAlreadyExistsException e) {
			responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return responseEntity;
	}

	@GetMapping
	public ResponseEntity<?> getAllNews() {
		ResponseEntity<?> responseEntity;
		List<News> news = newsService.getAllNews();
		responseEntity = new ResponseEntity<List<News>>(news, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping(path = "/{url}")
	public ResponseEntity<?> getNewsById(@PathVariable("url") String url) {
		ResponseEntity<?> responseEntity;
		try {
			News news = newsService.getNewsByUrl(url);
			responseEntity = new ResponseEntity<News>(news, HttpStatus.OK);
		} catch (NewsNotFoundException e) {
			responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteNewsById(@PathVariable("id") Integer id) {
		ResponseEntity<?> responseEntity;
		try {
			newsService.deleteNewsById(id);
			responseEntity = new ResponseEntity<String>("News removed from watchlist", HttpStatus.OK);
		} catch (NewsNotFoundException e) {
			responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return responseEntity;

	}

	@GetMapping(path = "/mynews")
	public ResponseEntity<?> getMyNews(ServletRequest req, ServletResponse res) {
		ResponseEntity<?> responseEntity;
		HttpServletRequest httpReq = (HttpServletRequest) req;
		String authHeader = httpReq.getHeader("authorization");
		String token = authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("secretKey").parseClaimsJws(token).getBody().getSubject();
		List<News> news = newsService.getMyNews(userId);
		responseEntity = new ResponseEntity<List<News>>(news, HttpStatus.OK);
		return responseEntity;
	}

}