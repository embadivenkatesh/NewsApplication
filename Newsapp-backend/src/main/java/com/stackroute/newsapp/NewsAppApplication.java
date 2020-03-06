package com.stackroute.newsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

import com.stackroute.newsapp.filter.JwtFilter;

@SpringBootApplication
public class NewsAppApplication {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public FilterRegistrationBean jwtFilter() {
		FilterRegistrationBean filterBean = new FilterRegistrationBean();
		filterBean.setFilter(new JwtFilter());
		filterBean.addUrlPatterns("/api/*");
		return filterBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(NewsAppApplication.class, args);
	}
}
