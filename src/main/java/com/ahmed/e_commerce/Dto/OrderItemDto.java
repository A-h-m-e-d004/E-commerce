package com.ahmed.e_commerce.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record OrderItemDto(

		@NotNull(message = "the orderId should not be empty")
		Long orderId,

		@NotEmpty(message = "the product title should not be empty")
		String productTitle,

		@Positive(message = "the quantity should not be empty")
		int quantity,

		@NotNull(message = "the price should not be empty")
		BigDecimal price
) {
}
