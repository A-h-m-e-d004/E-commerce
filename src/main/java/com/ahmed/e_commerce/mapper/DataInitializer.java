package com.ahmed.e_commerce.mapper;

import com.ahmed.e_commerce.Entity.User;
import com.ahmed.e_commerce.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@PostConstruct
	public void initData(){
		if (userRepository.count() == 0){
			User admin = new User();
			admin.setUsername("admin");
			admin.setPassword(passwordEncoder.encode("admin123"));
			admin.setRole(User.Role.ADMIN);
			userRepository.save(admin);
		}
	}
}
