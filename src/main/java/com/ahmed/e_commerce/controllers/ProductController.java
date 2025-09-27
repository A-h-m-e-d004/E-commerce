package com.ahmed.e_commerce.controllers;

import com.ahmed.e_commerce.Dto.ProductDto;
import com.ahmed.e_commerce.Dto.ProductResponseDto;
import com.ahmed.e_commerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping
	public ResponseEntity<?> addProduct(@Valid @RequestBody ProductDto productDto){
		productService.addProduct(productDto);
		return ResponseEntity.status(201).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long id){
		productService.deleteProductById(id);
		return ResponseEntity.status(200).build();
	}

	@PostMapping("{id}")
	public ResponseEntity<?> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDto productDto){
		productService.updateProductById(id, productDto);
		return ResponseEntity.status(200).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long id){
		return ResponseEntity.status(200).body(productService.getProductById(id));
	}

	@GetMapping
	public ResponseEntity<List<ProductResponseDto>> getAllProducts(){
		return ResponseEntity.status(200).body(productService.getAllProducts());
	}

	@GetMapping("/category/{categoryId}")
	public List<ProductResponseDto> getProductsByCategoryId(@PathVariable Long categoryId){
		return productService.getProductsByCategoryId(categoryId);
	}
}
