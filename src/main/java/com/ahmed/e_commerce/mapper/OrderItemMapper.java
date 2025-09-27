package com.ahmed.e_commerce.mapper;

import com.ahmed.e_commerce.Dto.OrderItemDto;
import com.ahmed.e_commerce.Dto.OrderItemResponseDto;
import com.ahmed.e_commerce.Entity.Order;
import com.ahmed.e_commerce.Entity.OrderItem;
import com.ahmed.e_commerce.Entity.Product;
import com.ahmed.e_commerce.repository.OrderRepository;
import com.ahmed.e_commerce.repository.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

	private final ProductRepository productRepository;

	private final OrderRepository orderRepository;

	public OrderItemMapper(ProductRepository productRepository, OrderRepository orderRepository) {
		this.productRepository = productRepository;
		this.orderRepository = orderRepository;
	}

	public OrderItem toOrderItem(OrderItemDto orderItemDto){
		OrderItem orderItem = new OrderItem();
		orderItem.setPrice(orderItemDto.price());
		orderItem.setQuantity(orderItemDto.quantity());
		Order order = orderRepository.findById(orderItemDto.orderId()).orElseThrow(() -> new RuntimeException("Order not found"));
		Product product = productRepository.findByTitle(orderItemDto.productTitle()).orElseThrow(() -> new RuntimeException("Product not found"));
		orderItem.setOrder(order);
		orderItem.setProduct(product);
		return orderItem;
	}

	public OrderItemResponseDto toOrderItemResponseDto(OrderItem orderItem){
		return new OrderItemResponseDto(
				orderItem.getId(),
				orderItem.getOrder().getId(),
				orderItem.getProduct().getTitle(),
				orderItem.getQuantity(),
				orderItem.getPrice()
		);
	}
}
