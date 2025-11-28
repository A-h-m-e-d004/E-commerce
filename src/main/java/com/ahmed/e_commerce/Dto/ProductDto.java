package com.ahmed.e_commerce.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductDto(

		@NotEmpty(message = "the title should not be empty")
		String title,

		@Positive(message = "the quantity should not be empty")
		int quantity,

		@Positive(message = "the price should not be empty")
        BigDecimal price,

		String description,

		@NotNull(message = "the categoryId should not be empty")
		Long categoryId
) {
}
