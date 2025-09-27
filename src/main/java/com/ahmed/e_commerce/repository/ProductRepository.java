package com.ahmed.e_commerce.repository;

import com.ahmed.e_commerce.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
	boolean existsByTitle(String title);

	@Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
	List<Product> findProductByCategoryId(Long categoryId);

	Optional<Product> findByTitle(String title);
}
