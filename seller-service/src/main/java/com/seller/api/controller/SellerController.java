package com.seller.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seller.api.model.Products;
import com.seller.api.repository.ProductRepository;
import com.seller.api.services.ProductService;

@RestController
@RequestMapping("/api")
public class SellerController {

	@Autowired
	ProductService productService;

	@Autowired
	ProductRepository productRepository;

	@PostMapping("/addproduct")
	public ResponseEntity<String> addProducts(@RequestBody Products products) throws Exception {

		try {
			productService.saveProducts(products);
			return ResponseEntity.status(HttpStatus.CREATED).body("Products Added Successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Products Cannot Be added");
		}
	}

	@GetMapping("/allproducts")
	List<Products> getComponents() {
		return productService.getProducts();
	}

	@GetMapping("/productmodel/{productModel}")
	public ResponseEntity<Products> getProductsById(@PathVariable("productModel") int productModel) {
		Optional<Products> prodData = productService.findByModel(productModel);
		if (prodData.isPresent()) {
			return new ResponseEntity<>(prodData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/productname/{productName}")
	public ResponseEntity<Products> getProductsById(@PathVariable("productName") String productName) {
		Optional<Products> prodData = productService.findByProductName(productName);
		if (prodData.isPresent()) {
			return new ResponseEntity<>(prodData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/productmake/{productMake}")
	public ResponseEntity<Products> getProductsByMake(@PathVariable("productMake") String productMake) {
		Optional<Products> prodData = productService.findByProductMake(productMake);
		if (prodData.isPresent()) {
			return new ResponseEntity<>(prodData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/productprice/{price}")
	public ResponseEntity<Products> getProductsByPrice(@PathVariable("price") float price) {
		Optional<Products> prodData = productService.findByPrice(price);
		if (prodData.isPresent()) {
			return new ResponseEntity<>(prodData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/products/{productModel}")
	public ResponseEntity<String> updateProducts(@PathVariable("productModel") int productModel,
			@RequestParam(name = "productstock", required = false, defaultValue = "0") Integer productStock,
			@RequestParam(name = "price", required = true, defaultValue = "0") Float price) {
		Optional<Products> productData = productRepository.findById(productModel);
		if (productData.isPresent()) {
			Products product = productData.get();
			product.setProductStock(productStock);
			product.setPrice(price);
			productRepository.save(product);
			return ResponseEntity.status(HttpStatus.OK).body("Products Updated Successfully");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Products Cannot Be Updated");
		}
	}

}
