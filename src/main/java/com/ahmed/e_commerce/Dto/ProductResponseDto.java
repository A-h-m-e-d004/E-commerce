package com.ahmed.e_commerce.Dto;

public record ProductResponseDto(
		Long id,
		String title,
		String description,
		int price,
		int quantity,
		String categoryTitle
) {
}
