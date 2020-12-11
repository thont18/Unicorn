package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ProductRepository;
import com.example.demo.models.Product;

@Service
@Transactional
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> findAll() {
		return productRepository.findAll();
	}
	
	public Optional<Product> get(Long id) {
		return productRepository.findById(id);
	}
	
	public Product save(Product product) {
		return productRepository.save(product);
	}
	
	public void delete(Long id) {
		productRepository.deleteById(id);
	}
}
