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
	private ProductRepository repo;

	public List<Product> listAll() {
		return repo.findAll();
	}

	public Optional<Product> get(Long id) {
		return repo.findById(id);
	}

	public Product save(Product pro) {
		return repo.save(pro);
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}
}
