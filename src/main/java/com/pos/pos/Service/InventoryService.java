package com.pos.pos.Service;

import com.pos.pos.Model.Inventory;
import com.pos.pos.Repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InventoryService implements InventoryServiceInterface {
	private final InventoryRepository inventoryRepository;

	@Override
	public List<Inventory> findAll() {
		return inventoryRepository.findAll();
	}

	@Override
	public Inventory findById(Long id) {
		return inventoryRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Inventory not found"));
	}

	@Override
	public Inventory create(Inventory inventory) {
		return inventoryRepository.save(inventory);
	}

	@Override
	public Inventory update(Long id, Integer quantity) {
		Inventory existingInventory = findById(id);
		existingInventory.setQuantity(quantity);
		return inventoryRepository.save(existingInventory);
	}

	@Override
	public void delete(Long id) {
		if (!inventoryRepository.existsById(id)) {
			throw new RuntimeException("Inventory not found");
		}
		inventoryRepository.deleteById(id);
	}

	@Override
	public boolean checkAvailableProduct(Long productId, Integer requestedQuantity) {
		if (productId == null || requestedQuantity == null || requestedQuantity <= 0) {
			throw new IllegalArgumentException("Invalid Parameters");
		}

		Integer availableStock = inventoryRepository.findQuantityByProductId(productId)
			.orElse(0);

		return availableStock >= requestedQuantity;
	}
}
