package com.ahmed.e_commerce.repository;

import com.ahmed.e_commerce.Entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByProductId(Long productId);
}
