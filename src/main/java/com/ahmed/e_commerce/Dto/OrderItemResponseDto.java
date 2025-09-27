package com.ahmed.e_commerce.Dto;

import java.math.BigDecimal;

public record OrderItemResponseDto(
		Long id,
		Long orderId,
		String productTitle,
		int quantity,
		BigDecimal price
) {
}
