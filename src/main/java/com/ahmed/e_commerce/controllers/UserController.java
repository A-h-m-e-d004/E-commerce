package com.ahmed.e_commerce.controllers;

import com.ahmed.e_commerce.Dto.UserResponseDto;
import com.ahmed.e_commerce.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<UserResponseDto> getAllUsers(){
		return userService.getAllUsers();
	}

	@GetMapping("/{id}")
	public UserResponseDto getUserById(@PathVariable Long id){
		return userService.getUserById(id);
	}

	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable Long id){
		userService.deleteUserById(id);
	}
}
