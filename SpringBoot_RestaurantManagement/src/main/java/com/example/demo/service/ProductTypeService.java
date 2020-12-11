package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ProductTypeRepository;
import com.example.demo.models.ProductType;

@Service
@Transactional
public class ProductTypeService {
	@Autowired
	private ProductTypeRepository typeRepository;
	
	public List<ProductType> findAll() {
		return typeRepository.findAll();
	}
	
	public Optional<ProductType> get(Long id) {
		return typeRepository.findById(id);
	}
	
	public ProductType save(ProductType type) {
		return typeRepository.save(type);
	}
	
	public void delete(Long id) {
		typeRepository.deleteById(id);
	}
}
