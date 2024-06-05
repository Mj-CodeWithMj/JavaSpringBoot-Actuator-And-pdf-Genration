package com.example.acutor.actuatorexample.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;

import com.example.acutor.actuatorexample.model.Product;
import com.example.acutor.actuatorexample.repository.ProductRepositroy;
import com.example.acutor.actuatorexample.service.ProductService;
import com.itextpdf.io.source.ByteArrayOutputStream;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
public class ProductController {
	
	@Autowired
	ProductRepositroy productRepositroy;
	
	@Autowired
	ProductService productService;

	@PostMapping("/productSave")
	public Product productSave(@RequestBody Product product) {
	 Product p = productRepositroy.save(product);
		return p;
	}
	@GetMapping("/productGet")
	public List<Product> productGet() {
		return productRepositroy.findAll();
	}
	@GetMapping("productGetById/{id}")
	public Optional<Product> productGetById(@PathVariable Long id) {
		Optional<Product> product = productRepositroy.findById(id); 
		return product;
	}
	@PutMapping("updateProduct/{id}")
	public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
		product.setId(id);
		return productRepositroy.save(product);
	}
	@DeleteMapping("deleteProduct/{id}")
	public void deleteProduct(@PathVariable Long id) {
		productRepositroy.deleteById(id);
	}
	
	@GetMapping("/getproductdata")
	public ResponseEntity<byte[]> product() throws Exception {
	
		ByteArrayOutputStream byteArrayOutputStream = productService.genratepdf();
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_PDF);
		
		httpHeaders.setContentDispositionFormData("attachment; ", "product-report.pdf");
		
		return ResponseEntity.ok().headers(httpHeaders).body(byteArrayOutputStream.toByteArray());
		
	}
	
	
	
	
}
