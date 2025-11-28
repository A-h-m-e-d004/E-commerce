package com.ahmed.e_commerce.Dto;

import java.math.BigDecimal;

public record ProductResponseDto(
		Long id,
		String title,
		String description,
		BigDecimal price,
		int quantity,
		String categoryTitle
) {
}
