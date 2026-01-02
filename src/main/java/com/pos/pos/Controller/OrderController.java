package com.pos.pos.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pos.pos.Model.Order;
import com.pos.pos.Service.OrderServiceInterface;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderController {
	private final OrderServiceInterface orderService;

	@GetMapping
	public List<Order> list() {
		return orderService.findAll();
	}

	@PostMapping
	public Order createOrder(Order order) {
		return orderService.create(order);
	}

	@GetMapping("/{id}")
	public Order getOrder(@PathVariable Long id) {
		return orderService.findById(id);
	}

	@DeleteMapping("/{id}")
	public void deleteOrder(@PathVariable Long id) {
		orderService.delete(id);
	}
}
