package com.stackroute.newsapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.newsapp.domain.News;
import com.stackroute.newsapp.exceptions.NewsAlreadyExistsException;
import com.stackroute.newsapp.exceptions.NewsNotFoundException;
import com.stackroute.newsapp.repository.NewsRepository;

@Service(value = "newsService")
public class NewsServiceImpl implements NewsService {

	@Autowired
	NewsRepository newsRepository;

	@Override
	public boolean saveNews(News news) throws NewsAlreadyExistsException {
		Optional<News> obj = newsRepository.findByUrl(news.getUrl());
		if (obj.isPresent()) {
			throw new NewsAlreadyExistsException("News already added to watchlist");
		}
		newsRepository.save(news);
		return true;
	}

	@Override
	public boolean deleteNewsById(int id) throws NewsNotFoundException {
		News news = newsRepository.findById(id).orElse(null);
		if (news == null) {
			throw new NewsNotFoundException("News doesn't exists to remove from watchlist");
		}
		newsRepository.delete(news);
		return true;
	}

	@Override
	public News getNewsByUrl(String url) throws NewsNotFoundException {
		News news = newsRepository.findByUrl(url).orElse(null);
		if (news == null) {
			throw new NewsNotFoundException("News not found");
		}
		return news;
	}

	@Override
	public List<News> getAllNews() {
		return newsRepository.findAll();
	}

	@Override
	public List<News> getMyNews(String userId) {
		return newsRepository.findByUserId(userId);
	}

}
