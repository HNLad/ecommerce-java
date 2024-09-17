package com.assign.ecommerce.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assign.ecommerce.model.ProductModel;
import com.assign.ecommerce.repository.ProductRepository;
import com.assign.ecommerce.service.ProductService;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<ProductModel> getAllProducts() {
		return productRepository.findAll();
	}

	@Transactional
	public ProductModel createProduct(ProductModel product) {
		if (productRepository.findById(product.getId()).isPresent()) {
			throw new RuntimeException("Product already exists");
		}
		return productRepository.save(product);
	}
}
