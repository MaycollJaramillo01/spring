package com.pos.pos.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pos.pos.Model.OrderItem;
import com.pos.pos.Service.OrderItemServiceInterface;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/order-items")
@RequiredArgsConstructor
public class OrderItemController {
	private final OrderItemServiceInterface orderItemService;

	@GetMapping
	public List<OrderItem> list() {
		return orderItemService.findAll();
	}

	@PostMapping
	public OrderItem createOrderItem(OrderItem orderItem) {
		return orderItemService.create(orderItem);
	}

	@GetMapping("/{id}")
	public OrderItem getOrderItem(@PathVariable Long id) {
		return orderItemService.findById(id);
	}

	@DeleteMapping("/{id}")
	public void deleteOrderItem(@PathVariable Long id) {
		orderItemService.delete(id);
	}
}
