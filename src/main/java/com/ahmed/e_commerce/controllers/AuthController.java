package com.ahmed.e_commerce.controllers;

import com.ahmed.e_commerce.Dto.UserDto;
import com.ahmed.e_commerce.config.JwtToken;
import com.ahmed.e_commerce.repository.UserRepository;
import com.ahmed.e_commerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final UserService userService;

	private final JwtToken jwtToken;

	private final PasswordEncoder passwordEncoder;

	private final UserRepository userRepository;

	public AuthController(UserService userService, JwtToken jwtToken, PasswordEncoder passwordEncoder, UserRepository userRepository) {
		this.userService = userService;
		this.jwtToken = jwtToken;
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
	}

	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@Valid @RequestBody UserDto userDto){
		var user = userRepository.findByUsername(userDto.username());
		if (user == null){
			return ResponseEntity.status(401).body("User not found");
		}
		if (passwordEncoder.matches(userDto.password(), user.getPassword())){
			String token = jwtToken.generateToken(userDto.username());
			return ResponseEntity.ok(token);
		}
		else {
			return ResponseEntity.status(401).body("Invalid password");
		}
	}

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@Valid @RequestBody UserDto userDto){
		userService.registerUser(userDto);
		return ResponseEntity.status(201).body("User registered successfully");
	}
}
