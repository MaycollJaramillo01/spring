package com.pos.pos.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pos.pos.Model.Product;
import com.pos.pos.Service.ProductServiceInterface;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductController {
	private final ProductServiceInterface productService;

	@GetMapping
	public List<Product> list() {
		return productService.findAll();
	}

	@GetMapping("/{barCode}")
	public Product scanProduct(@PathVariable String barCode) {
		return productService.findByBarCode(barCode);
	}

	@PostMapping
	public Product createProduct(Product product) {
		return productService.create(product);
	}

	@GetMapping("/{id}")
	public Product getProduct(@PathVariable Long id) {
		return productService.findById(id);
	}

	@PutMapping("/{id}")
	public Product updateProduct(@PathVariable Long id, Product product) {
		return productService.update(id, product);
	}

	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable Long id) {
		productService.delete(id);
	}
}
