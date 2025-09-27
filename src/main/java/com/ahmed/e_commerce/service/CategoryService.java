package com.ahmed.e_commerce.service;

import com.ahmed.e_commerce.Dto.CategoryDto;
import com.ahmed.e_commerce.Dto.CategoryResponseDto;
import com.ahmed.e_commerce.Entity.Category;
import com.ahmed.e_commerce.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public void createCategory(CategoryDto categoryDto){
		if(categoryRepository.existsByTitle(categoryDto.title())){
			throw new IllegalStateException("Category already exists");
		}
		categoryRepository.save(new Category(categoryDto.title()));
	}

	public List<CategoryResponseDto> getAllCategories(){
		return categoryRepository.findAll().stream().map(category -> new CategoryResponseDto(category.getId(), category.getTitle())).toList();
	}
}
