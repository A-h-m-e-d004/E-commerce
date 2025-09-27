package com.ahmed.e_commerce.mapper;

import com.ahmed.e_commerce.Dto.UserDto;
import com.ahmed.e_commerce.Dto.UserResponseDto;
import com.ahmed.e_commerce.Entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

	public UserResponseDto toUserResponseDto(User user){
		return new UserResponseDto(user.getId(), user.getUsername());
	}

	public User toUser(UserDto userDto){
		User user = new User();
		user.setUsername(userDto.username());
		user.setPassword(userDto.password());
		return user;
	}
}
