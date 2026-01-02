package com.pos.pos.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pos.pos.Model.Inventory;
import com.pos.pos.Service.InventoryServiceInterface;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/inventories")
@RequiredArgsConstructor
public class InventoryController {
	private final InventoryServiceInterface inventoryService;

	@GetMapping
	public List<Inventory> list() {
		return inventoryService.findAll();
	}

	@PostMapping
	public Inventory createInventory(Inventory inventory) {
		return inventoryService.create(inventory);
	}

	@GetMapping("/{id}")
	public Inventory getInventory(@PathVariable Long id) {
		return inventoryService.findById(id);
	}

	@PutMapping("/{id}")
	public Inventory updateInventory(@PathVariable Long id, Integer quantity) {
		return inventoryService.update(id, quantity);
	}

	@DeleteMapping("/{id}")
	public void deleteInventory(@PathVariable Long id) {
		inventoryService.delete(id);
	}
}
