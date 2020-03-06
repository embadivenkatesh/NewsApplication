package com.stackroute.newsapp.service;

import java.util.List;

import com.stackroute.newsapp.domain.News;
import com.stackroute.newsapp.exceptions.NewsAlreadyExistsException;
import com.stackroute.newsapp.exceptions.NewsNotFoundException;

public interface NewsService {

	boolean saveNews(News news) throws NewsAlreadyExistsException;

	boolean deleteNewsById(int id) throws NewsNotFoundException;

	News getNewsByUrl(String url) throws NewsNotFoundException;

	public List<News> getAllNews();

	public List<News> getMyNews(String userId);

}
