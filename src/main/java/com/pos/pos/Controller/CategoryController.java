package com.pos.pos.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pos.pos.Model.Category;
import com.pos.pos.Service.CategoryServiceInterface;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/categories")
@RequiredArgsConstructor
public class CategoryController {
	private final CategoryServiceInterface categoryService;

	@GetMapping
	public List<Category> list() {
		return categoryService.findAll();
	}

	@PostMapping
	public Category createCategory(Category category) {
		return categoryService.create(category);
	}

	@GetMapping("/{id}")
	public Category getCategory(@PathVariable Long id) {
		return categoryService.findById(id);
	}

	@PutMapping("/{id}")
	public Category updateCategory(@PathVariable Long id, Category category) {
		return categoryService.update(id, category);
	}

	@DeleteMapping("/{id}")
	public void deleteCategory(@PathVariable Long id) {
		categoryService.delete(id);
	}
}
