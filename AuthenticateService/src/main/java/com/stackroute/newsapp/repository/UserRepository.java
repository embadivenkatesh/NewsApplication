package com.stackroute.newsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.newsapp.domain.User;

public interface UserRepository extends JpaRepository<User, String> {
	public User findByUserIdAndPassword(String userId, String password);
}
