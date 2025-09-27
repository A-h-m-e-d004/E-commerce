package com.ahmed.e_commerce.mapper;

import com.ahmed.e_commerce.Dto.ProductDto;
import com.ahmed.e_commerce.Dto.ProductResponseDto;
import com.ahmed.e_commerce.Entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
	public Product toProduct(ProductDto productDto){
		Product product = new Product();
		product.setTitle(productDto.title());
		product.setQuantity(productDto.quantity());
		product.setPrice(productDto.price());
		product.setDescription(productDto.description());

		return product;
	}

	public ProductResponseDto toProductResponseDto(Product product){
		return new ProductResponseDto(
				product.getId(),
				product.getTitle(),
				product.getDescription(),
				product.getPrice(),
				product.getQuantity(),
				product.getCategory().getTitle()
		);
	}
}
