package com.ahmed.e_commerce.controllers;

import com.ahmed.e_commerce.Dto.CategoryDto;
import com.ahmed.e_commerce.Dto.CategoryResponseDto;
import com.ahmed.e_commerce.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@PostMapping("/create")
	public void createCategory(@Valid @RequestBody CategoryDto categoryDto){
		categoryService.createCategory(categoryDto);
	}

	@GetMapping("/all")
	public List<CategoryResponseDto> getAllCategories(){
		return categoryService.getAllCategories();
	}
}
