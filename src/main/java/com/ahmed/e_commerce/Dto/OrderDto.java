package com.ahmed.e_commerce.Dto;

import jakarta.validation.constraints.NotNull;

public record OrderDto(

		@NotNull(message = "the userId should not be empty")
		Long userId
) {
}
