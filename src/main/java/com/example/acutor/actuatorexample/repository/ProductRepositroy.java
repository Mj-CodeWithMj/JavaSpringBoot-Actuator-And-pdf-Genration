package com.example.acutor.actuatorexample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.acutor.actuatorexample.model.Product;

@Repository
public interface ProductRepositroy extends JpaRepository<Product, Long> {

	List<Product> findByPriceGreaterThan(int i);

}
