package com.ahmed.e_commerce.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtToken {

	private final String SECRET = "mySecretKeyThatMustBeAtLeast256BitsLongForSecurity";
	private  final int jwtExpiration = 86400;

	private SecretKey getKey(){
		return Keys.hmacShaKeyFor(SECRET.getBytes());
	}

	public String generateToken(String username){
		return Jwts.builder()
				.subject(username)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + jwtExpiration * 1000))
				.signWith(getKey())
				.compact();
	}

	public String getUsernameFromToken(String token){
		return Jwts.parser()
				.verifyWith(getKey())
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getSubject();
	}

	public boolean isTokenValid(String token){
		try {
			Jwts.parser()
					.verifyWith(getKey())
					.build()
					.parse(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
