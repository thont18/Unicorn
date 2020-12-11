//package com.example.demo.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.demo.exception.ResourceNotFoundException;
//import com.example.demo.models.Product;
//import com.example.demo.models.ProductStatus;
//import com.example.demo.models.ProductType;
//import com.example.demo.service.ProductService;
//import com.example.demo.service.ProductTypeService;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:3000")
//@RequestMapping(value = "/products")
//public class ProductController {
//	@Autowired
//	private ProductService productService;
//	@Autowired
//	private ProductTypeService typeService;
//
//	@GetMapping
//	public List<Product> getAllProducts() {
//		return this.productService.findAll();
//	}
//
//	@GetMapping("/{id}")
//	public Product getProduct(@PathVariable("id") Long id) {
//		return this.productService.get(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Booking Table ID: " + id + " NOT FOUND"));
//	}
//
//	@PostMapping
//	public ResponseEntity<?> createProduct(@RequestParam("code") String code,
//			@RequestParam("status") ProductStatus status, @RequestParam("siteId") Long siteId) {
//		Product newProduct = new Product();
//		ProductType site = workingSiteService.get(siteId).orElseThrow(() -> new ResourceNotFoundException("Site ID: " + siteId + " NOT FOUND"));;
//		newProduct.setCode(code);
//		newProduct.setStatus(status);
//		newProduct.setSite(site);
//
//		Product product = productService.save(newProduct);
//		HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.set("MyHeader", "MyValue");
//		return new ResponseEntity<>(product, httpHeaders, HttpStatus.CREATED);
//	}
//
//	@PutMapping(value = "/{id}")
//	public ResponseEntity<?> updateProduct(@PathVariable("id") Long id, @RequestParam("code") String code,
//			@RequestParam("status") ProductStatus status, @RequestParam("siteId") Long siteId) {
//		Product existingProduct = productService.get(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Product ID: " + id + " NOT FOUND"));
//		ProductType type = typeService.get(siteId).orElseThrow(() -> new ResourceNotFoundException("Product Type: " + siteId + " NOT FOUND"));
//		existingProduct.setCode(code);
//		existingProduct.setStatus(status);
//		existingProduct.setSite(site);
//
//		Product product = productService.save(existingProduct);
//		HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.set("MyHeader", "MyValue");
//		return new ResponseEntity<>(product, httpHeaders, HttpStatus.OK);
//	}
//
//	@DeleteMapping(value = "/{id}")
//	public void deleteProduct(@PathVariable("id") Long id) {
//		Product product = productService.get(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Product ID: " + id + " NOT FOUND"));
//		productService.delete(product.getId());
//	}
//}
