package com.ahmed.e_commerce.repository;

import com.ahmed.e_commerce.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	boolean existsByTitle(String title);
}
