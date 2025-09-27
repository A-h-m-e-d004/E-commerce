package com.ahmed.e_commerce.service;

import com.ahmed.e_commerce.Dto.ProductDto;
import com.ahmed.e_commerce.Dto.ProductResponseDto;
import com.ahmed.e_commerce.Entity.Category;
import com.ahmed.e_commerce.Entity.Product;
import com.ahmed.e_commerce.mapper.ProductMapper;
import com.ahmed.e_commerce.repository.CategoryRepository;
import com.ahmed.e_commerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	private final ProductMapper productMapper;

	private final CategoryRepository categoryRepository;

	public ProductService(ProductRepository productRepository, ProductMapper productMapper, CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.productMapper = productMapper;
		this.categoryRepository = categoryRepository;
	}

	public void addProduct(ProductDto productDto){
		if(productRepository.existsByTitle(productDto.title())){
			throw new IllegalStateException("Product already exists");
		}
		Product product = productMapper.toProduct(productDto);
		Category category = categoryRepository.findById(productDto.categoryId()).orElseThrow(() -> new RuntimeException("category not found"));
		product.setCategory(category);
		productRepository.save(product);
	}

	public void deleteProductById(Long id){
		if(!productRepository.existsById(id)){
			throw new IllegalStateException("Product with id " + id + " does not exist");
		}
		productRepository.deleteById(id);
	}

	public void updateProductById(Long id, ProductDto productDto){
		Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
		product.setTitle(productDto.title());
		product.setQuantity(productDto.quantity());
		product.setPrice(productDto.price());
		product.setDescription(productDto.description());
		Category category = categoryRepository.findById(productDto.categoryId()).orElseThrow(() -> new RuntimeException("Category not found"));
		product.setCategory(category);
		productRepository.save(product);
	}

	public ProductResponseDto getProductById(Long id){
		Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
		return new ProductResponseDto(
				product.getId(),
				product.getTitle(),
				product.getDescription(),
				product.getPrice(),
				product.getQuantity(),
				product.getCategory().getTitle()
		);
	}

	public List<ProductResponseDto> getAllProducts(){
		return productRepository.findAll().stream().map(product -> new ProductResponseDto(
				product.getId(),
				product.getTitle(),
				product.getDescription(),
				product.getPrice(),
				product.getQuantity(),
				product.getCategory().getTitle()
		)).toList();
	}

	public List<ProductResponseDto> getProductsByCategoryId(Long categoryId){
		return productRepository.findProductByCategoryId(categoryId).stream().map(productMapper::toProductResponseDto).toList();
	}
}
