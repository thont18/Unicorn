package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.example.demo.models.Product;
import com.example.demo.models.ProductStatus;
import com.example.demo.models.ProductType;
import com.example.demo.service.FileStorageService;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductTypeService;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService proSer;

	@Autowired
	private ProductTypeService proTypeSer;

	@Autowired
	private FileStorageService fileStorageService;

	@GetMapping()
	public ResponseEntity<List<Product>> listProduct() {
		List<Product> products = proSer.listAll();
		if (products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
		Optional<Product> product = proSer.get(id);

		if (!product.isPresent()) {
			return new ResponseEntity<>(product.get(), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(product.get(), HttpStatus.OK);
	}

	@PostMapping(consumes = "multipart/form-data")
	public ResponseEntity<?> createProduct(@RequestParam("code") String code, @RequestParam("name") String name,
			@RequestParam("proTypeId") Long proTypeId, @RequestParam("unit") String unit,
			@RequestParam("price") Double price, @RequestParam("status") ProductStatus status,
			@RequestParam("description") String description, @RequestParam("photo") MultipartFile photo) {
		if (photo.isEmpty()) {
			HttpStatus.valueOf("Photo not found!");
		}

		Product crePro = new Product();
		ProductType proType = proTypeSer.get(proTypeId);
		String fileName = code + "_" + fileStorageService.storeFile(photo);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
				.path(fileName).toUriString();
		crePro.setName(name);
		crePro.setProductType(proType);
		crePro.setUnit(unit);
		crePro.setPrice(price);
		crePro.setStatus(status);
		crePro.setCode(code);
		crePro.setDescription(description);
		crePro.setImage(fileDownloadUri);

		this.proSer.save(crePro);
		Product savePro = this.proSer.save(crePro);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("MyHeader", "MyValue");
		return new ResponseEntity<>(savePro, httpHeaders, HttpStatus.CREATED);
	}

	@PutMapping(value = "/updateProduct/{id}", consumes = "multipart/form-data")
	public ResponseEntity<?> updateProducts(@PathVariable("id") Long id, @RequestParam("code") String code,
			@RequestParam("name") String name, @RequestParam("proTypeId") Long proTypeId,
			@RequestParam("unit") String unit, @RequestParam("price") Double price,
			@RequestParam("status") ProductStatus status, @RequestParam("description") String description,
			@RequestParam("photo") MultipartFile photo) {
		if (photo.isEmpty()) {
			HttpStatus.valueOf("Photo not found!");
		}

		Product crePro = proSer.get(id).get();
		ProductType proType = proTypeSer.get(proTypeId);
		String fileName = code + "_" + fileStorageService.storeFile(photo);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
				.path(fileName).toUriString();
		crePro.setName(name);
		crePro.setProductType(proType);
		crePro.setUnit(unit);
		crePro.setPrice(price);
		crePro.setStatus(status);
		crePro.setCode(code);
		crePro.setDescription(description);
		crePro.setImage(fileDownloadUri);

		this.proSer.save(crePro);
		Product savePro = this.proSer.save(crePro);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("MyHeader", "MyValue");
		return new ResponseEntity<>(savePro, httpHeaders, HttpStatus.CREATED);
	}

	@PutMapping(value = "/updateProductWTI/{id}", consumes = "multipart/form-data")
	public ResponseEntity<?> updateProductWTI(@PathVariable("id") Long id, @RequestParam("code") String code,
			@RequestParam("name") String name, @RequestParam("proTypeId") Long proTypeId,
			@RequestParam("unit") String unit, @RequestParam("price") Double price,
			@RequestParam("status") ProductStatus status, @RequestParam("description") String description) {

		Product crePro = proSer.get(id).get();
		ProductType proType = proTypeSer.get(proTypeId);

		crePro.setName(name);
		crePro.setProductType(proType);
		crePro.setUnit(unit);
		crePro.setPrice(price);
		crePro.setStatus(status);
		crePro.setCode(code);
		crePro.setDescription(description);

		this.proSer.save(crePro);
		Product savePro = this.proSer.save(crePro);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("MyHeader", "MyValue");
		return new ResponseEntity<>(savePro, httpHeaders, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public Product deleteProduct(@PathVariable("id") Long id) {
		Product product = proSer.get(id).get();
		proSer.delete(product.getId());
		return product;
	}
}
