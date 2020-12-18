package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ProductTypeRepository;
import com.example.demo.models.ProductType;

@Service
@Transactional
public class ProductTypeService {
	@Autowired
	private ProductTypeRepository repo;

	public List<ProductType> listAll() {
		return repo.findAll();
	}
	
	public Page<ProductType> listAll(Pageable pageable, String searchText) {
		return repo.findAllTypes(pageable, searchText);
	}
	
	public Page<ProductType> findAll(Pageable pageable) {
//		return repo.findAllTypesAscending(pageable);
		return repo.findAll(pageable);
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
