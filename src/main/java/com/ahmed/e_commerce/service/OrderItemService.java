package com.ahmed.e_commerce.service;

import com.ahmed.e_commerce.Dto.OrderItemDto;
import com.ahmed.e_commerce.Dto.OrderItemResponseDto;
import com.ahmed.e_commerce.Entity.OrderItem;
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
        int newQuantity = product.getQuantity() - orderItemDto.quantity();
        product.setQuantity(newQuantity);
        productRepository.save(product);
		orderItemRepository.save(orderItemMapper.toOrderItem(orderItemDto));
	}

    public void updateOrderItemQuantity(Long cardId, Long productId, int quantity){
		if(!orderItemRepository.existsById(cardId)){
			throw new RuntimeException("Order item not found");
		}
        OrderItem oldOrderItem = orderItemRepository.findById(cardId).orElseThrow(() -> new RuntimeException("Order item not found"));
		Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        if (quantity > product.getQuantity()) {
            throw new RuntimeException("Not enough quantity");
        }
		int newQuantity = product.getQuantity() + oldOrderItem.getQuantity() - quantity;
		if(newQuantity < 0){
			throw new RuntimeException("Not enough quantity");
		}
		product.setQuantity(newQuantity);
		productRepository.save(product);
		orderItemRepository.findById(cardId).ifPresent(orderItem -> {
			orderItem.setQuantity(quantity);
			orderItem.calculateTotalPrice();
			orderItemRepository.save(orderItem);
		});
	}

    public void deleteOrderItem(Long cardId){
		if(!orderItemRepository.existsById(cardId)){
			throw new RuntimeException("Order item not found");
		}
		orderItemRepository.deleteById(cardId);
	}

	public List<OrderItemResponseDto> getAllOrderItems(Long orderId){
		if (!orderItemRepository.existsByOrderId(orderId)){
			throw new IllegalStateException("Order not exist");
		}
		return orderItemRepository.findByOrderId(orderId).stream().map(orderItemMapper::toOrderItemResponseDto).toList();
	}
}
