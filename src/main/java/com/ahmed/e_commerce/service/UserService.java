package com.ahmed.e_commerce.service;

import com.ahmed.e_commerce.Dto.UserDto;
import com.ahmed.e_commerce.Dto.UserResponseDto;
import com.ahmed.e_commerce.Entity.User;
import com.ahmed.e_commerce.mapper.UserMapper;
import com.ahmed.e_commerce.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

	private final UserRepository userRepository;

	private final UserMapper userMapper;

	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.passwordEncoder = passwordEncoder;
	}

	public void registerUser(UserDto userDto){
		if(userRepository.existsByUsername(userDto.username())){
			throw new IllegalStateException("Username already taken");
		}
		User user = userMapper.toUser(userDto);
		user.setRole(User.Role.USER);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	public List<UserResponseDto> getAllUsers(){
		return userRepository.findAll().stream().map(userMapper::toUserResponseDto).toList();
	}

	public UserResponseDto getUserById(Long id){
		return userMapper.toUserResponseDto(userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found")));
	}

	public void deleteUserById(Long id){
		if(!userRepository.existsById(id)){
			throw new IllegalStateException("User with id " + id + " does not exist");
		}
		userRepository.deleteById(id);
	}
}
