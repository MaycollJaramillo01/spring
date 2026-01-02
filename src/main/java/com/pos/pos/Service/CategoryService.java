package com.pos.pos.Service;

import com.pos.pos.Model.Category;
import com.pos.pos.Repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService implements CategoryServiceInterface {
	private final CategoryRepository categoryRepository;

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category findById(Long id) {
		return categoryRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Category not found"));
	}

	@Override
	public Category create(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Category update(Long id, Category category) {
		Category existingCategory = findById(id);
		existingCategory.setName(category.getName());
		existingCategory.setDescription(category.getDescription());
		return categoryRepository.save(existingCategory);
	}

	@Override
	public void delete(Long id) {
		if (!categoryRepository.existsById(id)) {
			throw new RuntimeException("Category not found");
		}
		categoryRepository.deleteById(id);
	}
}

