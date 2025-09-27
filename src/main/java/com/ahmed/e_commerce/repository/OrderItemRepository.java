package com.ahmed.e_commerce.repository;

import com.ahmed.e_commerce.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	boolean existsByProductTitleAndOrderId(String productTitle, Long orderId);
	boolean existsByOrderId(Long orderId);
	List<OrderItem> findByOrderId(Long orderId);
}
