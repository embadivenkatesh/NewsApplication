package com.stackroute.newsapp.tokengenerator;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import com.stackroute.newsapp.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenGenerator implements TokenGenerator {

	@Override
	public Map<String, String> generateToken(User user) {
		String jwtToken = "";
		jwtToken = Jwts.builder().setSubject(user.getUserId()).setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretKey").compact();

		Map<String, String> tokenmap = new HashMap<String, String>();
		tokenmap.put("token", jwtToken);
		tokenmap.put("message", "Token created successfully");
		return tokenmap;
	}

}
