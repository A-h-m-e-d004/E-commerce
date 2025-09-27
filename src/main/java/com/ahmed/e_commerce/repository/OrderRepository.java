package com.ahmed.e_commerce.repository;

import com.ahmed.e_commerce.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
