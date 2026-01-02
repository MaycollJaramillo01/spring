package com.pos.pos.Service;

import com.pos.pos.Model.Order;
import com.pos.pos.Repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService implements OrderServiceInterface {
	private final OrderRepository orderRepository;

	@Override
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	@Override
	public Order findById(Long id) {
		return orderRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Order not found"));
	}

	@Override
	public Order create(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public void delete(Long id) {
		if (!orderRepository.existsById(id)) {
			throw new RuntimeException("Order not found");
		}
		orderRepository.deleteById(id);
	}
}

