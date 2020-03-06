package com.stackroute.newsapp.tokengenerator;

import java.util.Map;

import com.stackroute.newsapp.domain.User;

public interface TokenGenerator {
	Map<String, String> generateToken(User userId);
}
