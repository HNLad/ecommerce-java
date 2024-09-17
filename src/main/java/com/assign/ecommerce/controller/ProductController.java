package com.assign.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assign.ecommerce.model.ProductModel;
import com.assign.ecommerce.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/getAllProducts")
	public List<ProductModel> getAllProducts() {
		return productService.getAllProducts();
	}

	@PostMapping("/createProduct")
	public ResponseEntity<ProductModel> createProduct(@RequestBody ProductModel product) {
		if (product != null) {
			ProductModel createdProduct = productService.createProduct(product);
			return ResponseEntity.ok(createdProduct);
		}
		return ResponseEntity.ok(null);
	}
}
