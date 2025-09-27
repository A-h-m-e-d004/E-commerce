package com.ahmed.e_commerce.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

	private final JwtToken jwtToken;

	private final UserDetailServiceImp userDetailServiceImp;

	public JwtFilter(JwtToken jwtToken, UserDetailServiceImp userDetailServiceImp) {
		this.jwtToken = jwtToken;
		this.userDetailServiceImp = userDetailServiceImp;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String token = getTokenFromRequest(request);
		if(token != null && jwtToken.isTokenValid(token)){
			String username = jwtToken.getUsernameFromToken(token);
			UserDetails userDetails = userDetailServiceImp.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		filterChain.doFilter(request, response);
	}

	private String getTokenFromRequest(HttpServletRequest http){
		final String authHeader = http.getHeader("Authorization");
		if(authHeader != null && authHeader.startsWith("Bearer ")){
			return authHeader.substring(7);
		}
		return null;
	}
}
