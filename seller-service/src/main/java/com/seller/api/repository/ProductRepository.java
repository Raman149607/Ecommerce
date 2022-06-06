package com.seller.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seller.api.model.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {

	Optional<Products> findByProductName(String productName);

	Optional<Products> findByProductMake(String productMake);

	Optional<Products> findByPrice(float price);

}