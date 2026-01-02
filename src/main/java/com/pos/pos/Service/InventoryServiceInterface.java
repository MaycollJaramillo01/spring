package com.pos.pos.Service;

import com.pos.pos.Model.Inventory;
import java.util.List;

public interface InventoryServiceInterface {
	List<Inventory> findAll();
	Inventory findById(Long id);
	Inventory create(Inventory inventory);
	Inventory update(Long id, Integer quantity);
	void delete(Long id);
	boolean checkAvailableProduct(Long productId, Integer requestedQuantity);
}
