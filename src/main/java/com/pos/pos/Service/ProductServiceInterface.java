package com.pos.pos.Service;

import com.pos.pos.Model.Product;
import java.util.List;

public interface ProductServiceInterface {
	List<Product> findAll();
	Product findById(Long id);
	Product findByBarCode(String barCode);
	Product create(Product product);
	Product update(Long id, Product product);
	void delete(Long id);
	boolean existsByBarCode(String barCode);
}

