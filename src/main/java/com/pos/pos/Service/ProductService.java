package com.pos.pos.Service;

import com.pos.pos.Model.Product;
import com.pos.pos.Repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService implements ProductServiceInterface {
	private final ProductRepository productRepository;

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Product findById(Long id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found"));
	}

	@Override
	public Product findByBarCode(String barCode) {
		return productRepository.findByBarCode(barCode);
	}

	@Override
	public Product create(Product product) {
		if (product.getBarCode() != null && productRepository.existsByBarCode(product.getBarCode())) {
			throw new IllegalArgumentException("Bar Code Already Exists");
		}
		return productRepository.save(product);
	}

	@Override
	public Product update(Long id, Product product) {
		Product existingProduct = findById(id);
		existingProduct.setName(product.getName());
		existingProduct.setIsActive(product.getIsActive());
		existingProduct.setCostPrice(product.getCostPrice());
		existingProduct.setBarCode(product.getBarCode());
		existingProduct.setMeasureUnit(product.getMeasureUnit());
		existingProduct.setBrand(product.getBrand());
		existingProduct.setDescription(product.getDescription());
		existingProduct.setTaxPercentage(product.getTaxPercentage());
		existingProduct.setProductCategories(product.getProductCategories());
		return productRepository.save(existingProduct);
	}

	@Override
	public void delete(Long id) {
		if (!productRepository.existsById(id)) {
			throw new RuntimeException("Product not found");
		}
		productRepository.deleteById(id);
	}

	@Override
	public boolean existsByBarCode(String barCode) {
		return productRepository.existsByBarCode(barCode);
	}
}

