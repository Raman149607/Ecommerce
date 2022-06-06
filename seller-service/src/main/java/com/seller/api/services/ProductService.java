package com.seller.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seller.api.model.Products;
import com.seller.api.repository.ProductRepository;

@Service
@Transactional
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public Products saveProducts(Products products) {
		return productRepository.save(products);
	}

	public List<Products> getProducts() {
		List<Products> products = productRepository.findAll();
		return products;
	}

	public Optional<Products> findByModel(int productModel) {
		return productRepository.findById(productModel);
	}

	public Optional<Products> findByProductName(String productName) {
		return productRepository.findByProductName(productName);
	}

	public Optional<Products> findByProductMake(String productMake) {
		return productRepository.findByProductMake(productMake);
	}


	public Optional<Products> findByPrice(float price) {
		return productRepository.findByPrice(price);
	}

}
