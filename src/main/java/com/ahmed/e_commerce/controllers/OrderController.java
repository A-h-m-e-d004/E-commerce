package com.ahmed.e_commerce.controllers;

import com.ahmed.e_commerce.Dto.OrderDto;
import com.ahmed.e_commerce.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping
	public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody OrderDto orderDto) {
		orderService.createOrder(orderDto);
		return ResponseEntity.ok(orderDto);
	}
}
