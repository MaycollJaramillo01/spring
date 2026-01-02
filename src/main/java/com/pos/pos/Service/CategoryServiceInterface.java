package com.pos.pos.Service;

import com.pos.pos.Model.Category;
import java.util.List;

public interface CategoryServiceInterface {
	List<Category> findAll();
	Category findById(Long id);
	Category create(Category category);
	Category update(Long id, Category category);
	void delete(Long id);
}

