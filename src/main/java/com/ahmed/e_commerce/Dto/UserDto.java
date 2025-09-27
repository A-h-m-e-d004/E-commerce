package com.ahmed.e_commerce.Dto;

import jakarta.validation.constraints.NotEmpty;

public record UserDto(
		@NotEmpty(message = "the user name should not be empty")
		String username,

		@NotEmpty(message = "the password should not be empty")
		String password
) {
}
