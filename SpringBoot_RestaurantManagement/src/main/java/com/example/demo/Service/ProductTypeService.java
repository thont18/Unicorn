package com.example.demo.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ProductTypeRepostitory;
import com.example.demo.models.ProductType;

@Service
@Transactional
public class ProductTypeService {
	@Autowired
	private ProductTypeRepostitory repo;

	public List<ProductType> listAll() {
		return repo.findAll();
	}

	public ProductType get(Long id) {
		return repo.findById(id).get();
	}

	public ProductType save(ProductType proType) {
		return repo.save(proType);
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}
}
