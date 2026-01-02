package com.pos.pos.Service;

import com.pos.pos.Model.Order;
import java.util.List;

public interface OrderServiceInterface {
	List<Order> findAll();
	Order findById(Long id);
	Order create(Order order);
	void delete(Long id);
}

