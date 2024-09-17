package com.assign.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assign.ecommerce.model.ProductModel;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {
	
	ProductModel findById(long id);
}
