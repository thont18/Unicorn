 package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.models.ProductType;
import com.example.demo.service.ProductTypeService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("productTypes")
public class ProductTypeController {
	@Autowired
	private ProductTypeService ser;
	@GetMapping("/getAllProductType")	
	public List<ProductType> getAllProducts() {
		return this.ser.listAll();
	}


	@GetMapping()
	public ResponseEntity<Page<ProductType>> getProductTypes(int pageNumber, int pageSize, String sortBy,
			String sortDir) {
		return new ResponseEntity<Page<ProductType>>(
				ser.findAll(PageRequest.of(pageNumber, pageSize,
						sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending())),
				HttpStatus.OK);
	}
	
	@GetMapping("/search/{searchText}")
	public ResponseEntity<Page<ProductType>> searchProductTypes(Pageable pageable, @PathVariable String searchText) {
		return new ResponseEntity<>(ser.listAll(pageable, searchText), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductType> getProductType(@PathVariable("id") Long id) {
		return new ResponseEntity<ProductType>(this.ser.get(id).orElseThrow(() -> new ResourceNotFoundException("Not found product type ID: " + id)), HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<ProductType> createProductType(@RequestBody ProductType proType,
			UriComponentsBuilder builder) {
		ser.save(proType);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/productTypes/{id}").buildAndExpand(proType.getId()).toUri());
		return new ResponseEntity<>(proType, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductType> updateProductType(@RequestBody @Validated ProductType productType,
			@PathVariable Long id) {
		ProductType proType = ser.get(id).orElseThrow(() -> new ResourceNotFoundException("Not found product type ID: " + id));

		if (proType == null) {
			return new ResponseEntity<ProductType>(HttpStatus.NOT_FOUND);
		}

		proType.setName(productType.getName());
		proType.setDescription(productType.getDescription());
		ser.save(proType);
		return new ResponseEntity<ProductType>(proType, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProductType(@PathVariable("id") Long id) {
		this.ser.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
