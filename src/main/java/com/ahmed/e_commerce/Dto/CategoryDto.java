package com.ahmed.e_commerce.Dto;

import jakarta.validation.constraints.NotEmpty;

public record CategoryDto(

		@NotEmpty(message = "title is required")
		String title
) {
}
