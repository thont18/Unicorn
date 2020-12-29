package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.ProductRepository;
import com.example.demo.models.Product;
import com.example.demo.models.ProductType;

@Service
@Transactional
public class ProductService {
	@Autowired
	private ProductRepository repo;

	public Page<Product> listAll(Pageable pageable, String searchText) {
		return repo.findAllProducts(pageable, searchText);
	}
	
	public List<Product> listAll() {
		return repo.findAll();
	}
	
	public Page<Product> listAll(Pageable pageable) {
		return repo.findAllProductsAscending(pageable);
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
	
<<<<<<< HEAD
	public List<String> check(@RequestParam("newCode") String newCode) {
=======
	public List<String> checkCode(@RequestParam("newCode") String newCode) {
>>>>>>> 8f4859ad739749e36073266c8243523d11bc0a09
		return repo.checkCode(newCode);
	}
}
