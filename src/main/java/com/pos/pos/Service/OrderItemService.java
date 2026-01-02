package com.pos.pos.Service;

import com.pos.pos.Model.OrderItem;
import com.pos.pos.Repository.OrderItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderItemService implements OrderItemServiceInterface {
	private final InventoryServiceInterface inventoryService;
	private final OrderItemRepository orderItemRepository;

	@Override
	public List<OrderItem> findAll() {
		return orderItemRepository.findAll();
	}

	@Override
	public OrderItem findById(Long id) {
		return orderItemRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("OrderItem not found"));
	}

	@Override
	public OrderItem create(OrderItem orderItem) {
		Integer requestedQuantity = orderItem.getQuantity();
		if (!inventoryService.checkAvailableProduct(orderItem.getProduct().getId(), requestedQuantity)) {
			throw new IllegalArgumentException("Not Enough of This Product Available");
		}
		return orderItemRepository.save(orderItem);
	}

	@Override
	public void delete(Long id) {
		if (!orderItemRepository.existsById(id)) {
			throw new RuntimeException("OrderItem not found");
		}
		orderItemRepository.deleteById(id);
	}
}
