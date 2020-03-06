package com.stackroute.newsapp.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.newsapp.domain.News;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
public class NewsRepositoryTest {

	@Autowired
	private NewsRepository newsRepository;

	@Test
	public void testSaveNews() throws Exception {
		newsRepository.save(new News(1, "Venkatesh", "movie news", "www.bbc.com", "wow", "google.com", "123", "526645"));
		News news = newsRepository.getOne(1);
		assertThat(news.getId()).isEqualTo(1);
	}

	@Test
	public void testGetAllNews() throws Exception {
		newsRepository.deleteAll();
		newsRepository.save(new News(2, "Venkatesh", "movie news", "www.bbc.com", "wow", "google.com", "123", "526645"));
		newsRepository.save(new News(3, "Embadi", "sports news", "www.bbc.com", "wow", "google.com", "123", "526645"));
		List<News> newsList = newsRepository.findAll();
		assertEquals(newsList.size(), 2);
	}

	@Test
	public void testFindByUserId() throws Exception {
		newsRepository.save(new News(1, "Venkatesh", "movie news", "www.bbc.com", "wow", "google.com", "123", "526645"));
		newsRepository.save(new News(2, "Embadi", "sports news", "www.bbc.com", "wow", "google.com", "123", "526645"));
		List<News> newsList = newsRepository.findByUserId("526645");
		assertEquals(newsList.get(0).getAuthor(), "Venkatesh");
	}
}