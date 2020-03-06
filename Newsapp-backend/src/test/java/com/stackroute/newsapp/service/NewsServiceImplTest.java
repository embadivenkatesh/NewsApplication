package com.stackroute.newsapp.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.stackroute.newsapp.domain.News;
import com.stackroute.newsapp.exceptions.NewsAlreadyExistsException;
import com.stackroute.newsapp.exceptions.NewsNotFoundException;
import com.stackroute.newsapp.repository.NewsRepository;

public class NewsServiceImplTest {
	
    @Mock	
    private transient NewsRepository newsRepository;
	
	@InjectMocks
    private transient NewsServiceImpl newsServiceImpl;
	
	static transient News news;
	
	private transient Optional<News> opt;	
	
	@Before
	public void setupmock(){
		MockitoAnnotations.initMocks(this);
		news = new News(1, "Venkatesh", "movie news", "www.bbc.com", "wow", "google.com", "123", "526645");
		opt= Optional.of(news);
		
		
	}
	
	@Test
	public void testSaveNewsSuccess() throws NewsAlreadyExistsException {
		Mockito.when(newsRepository.save(news)).thenReturn(news);
		boolean isSaved = newsServiceImpl.saveNews(news);
		assertTrue(isSaved);
		Mockito.verify(newsRepository, times(1)).save(news);
		Mockito.verify(newsRepository, times(1)).findByUrl(news.getUrl());
	}
	
	@Test(expected=NewsAlreadyExistsException.class)
	public void testSaveNewsFailure() throws NewsAlreadyExistsException {
		Mockito.when(newsRepository.save(news)).thenReturn(news);
		Mockito.when(newsRepository.findByUrl(news.getUrl())).thenReturn(opt);
		boolean isSaved = newsServiceImpl.saveNews(news);
		assertFalse("News failed to save",isSaved);		
		Mockito.verify(newsRepository,times(1)).findById(news.getId());
	}
	
	@Test
	public void testDeleteNews() throws NewsNotFoundException {
		doNothing().when(newsRepository).delete(news);
		Mockito.when(newsRepository.findById(news.getId())).thenReturn(opt);
		boolean isDeleted = newsServiceImpl.deleteNewsById(news.getId());
		assertTrue("delete news success", isDeleted);
		Mockito.verify(newsRepository, times(1)).delete(news);
		Mockito.verify(newsRepository, times(1)).findById(news.getId());
	}
	
	@Test
	public void getMyNews() {
		List<News> newsList = new ArrayList<News>();
		newsList.add(news);
		Mockito.when(newsRepository.findByUserId(news.getUserId())).thenReturn(newsList);
		newsServiceImpl.getMyNews(news.getUserId());
		Mockito.verify(newsRepository, times(1)).findByUserId(news.getUserId());
	}	
}