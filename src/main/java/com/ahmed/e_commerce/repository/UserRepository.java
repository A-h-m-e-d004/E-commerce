package com.ahmed.e_commerce.repository;

import com.ahmed.e_commerce.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
	boolean existsByUsername(String username);
}
