package com.pos.pos.Service;

import com.pos.pos.Model.OrderItem;
import java.util.List;

public interface OrderItemServiceInterface {
	List<OrderItem> findAll();
	OrderItem findById(Long id);
	OrderItem create(OrderItem orderItem);
	void delete(Long id);
}

