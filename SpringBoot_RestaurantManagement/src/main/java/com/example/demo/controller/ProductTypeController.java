package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.models.ProductType;
import com.example.demo.service.ProductTypeService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/types")
public class ProductTypeController {
	@Autowired
	private ProductTypeService typeService;
	
	@GetMapping
	public List<ProductType> getAllProductTypes() {
		return this.typeService.findAll();
	}

	@GetMapping("/{id}")
	public ProductType getProductType(@PathVariable("id") Long id) {
		return this.typeService.get(id).orElseThrow(() -> new ResourceNotFoundException("ProductType ID: " + id + " NOT FOUND"));
	}

	@PostMapping
	public ProductType createProductType(@RequestBody ProductType type) {
		return typeService.save(type);
	}

	@PutMapping("/{id}")
	public ProductType updateProductType(@RequestBody @Validated ProductType type, @PathVariable("id") Long id) {
		typeService.get(id).orElseThrow(() -> new ResourceNotFoundException("ProductType ID: " + id + " NOT FOUND"));
		return typeService.save(type);
	}

	@DeleteMapping("/{id}")
	public void deleteProductType(@PathVariable("id") Long id) {
		ProductType type = typeService.get(id).orElseThrow(() -> new ResourceNotFoundException("ProductType ID: " + id + " NOT FOUND"));
		typeService.delete(type.getId());
	}
}
