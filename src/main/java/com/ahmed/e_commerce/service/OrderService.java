package com.ahmed.e_commerce.service;

import com.ahmed.e_commerce.Dto.OrderDto;
import com.ahmed.e_commerce.Entity.Order;
import com.ahmed.e_commerce.Entity.User;
import com.ahmed.e_commerce.repository.OrderRepository;
import com.ahmed.e_commerce.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

	private final OrderRepository orderRepository;

	private final UserRepository userRepository;

	public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
		this.orderRepository = orderRepository;
		this.userRepository = userRepository;
	}

	public void createOrder(OrderDto orderDto){
		User user = userRepository.findById(orderDto.userId()).orElseThrow(() -> new RuntimeException("User not found"));
		Order order = new Order();
		order.setUser(user);
		orderRepository.save(order);
	}
}
