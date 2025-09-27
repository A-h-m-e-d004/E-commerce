package com.ahmed.e_commerce.service;

import com.ahmed.e_commerce.Dto.OrderItemDto;
import com.ahmed.e_commerce.Dto.OrderItemResponseDto;
import com.ahmed.e_commerce.Entity.Product;
import com.ahmed.e_commerce.mapper.OrderItemMapper;
import com.ahmed.e_commerce.repository.OrderItemRepository;
import com.ahmed.e_commerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

	private final OrderItemRepository orderItemRepository;

	private final OrderItemMapper orderItemMapper;

	private final ProductRepository productRepository;

	public OrderItemService(OrderItemRepository orderItemRepository, OrderItemMapper orderItemMapper, ProductRepository productRepository) {
		this.orderItemRepository = orderItemRepository;
		this.orderItemMapper = orderItemMapper;
		this.productRepository = productRepository;
	}

	public void createOrderItem(OrderItemDto orderItemDto){
		if(orderItemRepository.existsByProductTitleAndOrderId(orderItemDto.productTitle(), orderItemDto.orderId())){
			throw new RuntimeException("Order item already exists");
		}
		Product product = productRepository.findByTitle(orderItemDto.productTitle()).orElseThrow(() -> new RuntimeException("Product not found"));
		if(product.getQuantity() < orderItemDto.quantity()){
			throw new RuntimeException("Not enough quantity");
		}
		orderItemRepository.save(orderItemMapper.toOrderItem(orderItemDto));
	}

	public List<OrderItemResponseDto> getAllOrderItems(Long orderId){
		if (!orderItemRepository.existsByOrderId(orderId)){
			throw new IllegalStateException("Order not exist");
		}
		return orderItemRepository.findByOrderId(orderId).stream().map(orderItemMapper::toOrderItemResponseDto).toList();
	}
}
