package com.ahmed.e_commerce.controllers;

import com.ahmed.e_commerce.Dto.OrderItemDto;
import com.ahmed.e_commerce.Dto.OrderItemResponseDto;
import com.ahmed.e_commerce.service.OrderItemService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderItem")
public class OrderItemController {

	private final OrderItemService orderItemService;

	public OrderItemController(OrderItemService orderItemService) {
		this.orderItemService = orderItemService;
	}

	@PostMapping
	public ResponseEntity<OrderItemDto> createOrderItem(@Valid @RequestBody OrderItemDto orderItemDto) {
		orderItemService.createOrderItem(orderItemDto);
		return ResponseEntity.ok(orderItemDto);
	}

    @PutMapping("card/{cardId}/product/{productId}")
	public ResponseEntity<OrderItemDto> updateOrderItem(@PathVariable Long cardId, @PathVariable Long productId, @RequestParam int quantity) {
		orderItemService.updateOrderItemQuantity(cardId, productId, quantity);
		return ResponseEntity.ok().build();
	}

    @DeleteMapping("/{cardId}")
	public ResponseEntity<OrderItemDto> deleteOrderItem(@PathVariable Long cardId) {
		orderItemService.deleteOrderItem(cardId);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{orderId}")
	public List<OrderItemResponseDto> getAllOrderItems(@PathVariable Long orderId){
		return orderItemService.getAllOrderItems(orderId);
	}
}
