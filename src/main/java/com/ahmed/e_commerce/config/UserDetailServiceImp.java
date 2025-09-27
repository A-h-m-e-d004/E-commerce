package com.ahmed.e_commerce.config;

import com.ahmed.e_commerce.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImp implements UserDetailsService {

	private final UserRepository userRepository;

	public UserDetailServiceImp(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = userRepository.findByUsername(username);
		return User.builder()
				.username(user.getUsername())
				.password(user.getPassword())
				.authorities("ROLE_" + user.getRole().name())
				.build();
	}
}
