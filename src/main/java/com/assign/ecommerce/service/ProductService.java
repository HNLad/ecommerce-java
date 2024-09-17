package com.assign.ecommerce.service;

import java.util.List;

import com.assign.ecommerce.model.ProductModel;

public interface ProductService {
	
	public List<ProductModel> getAllProducts();
	
	public ProductModel createProduct(ProductModel product);

}
