package com.ahmed.e_commerce.service;

import com.ahmed.e_commerce.Entity.User;
import com.ahmed.e_commerce.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

	private final UserRepository userRepository;

	public AdminService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void updateUserRole(Long id, User.Role role){
		var user = userRepository.findById(id).orElseThrow();
		user.setRole(role);
		userRepository.save(user);
	}
}
