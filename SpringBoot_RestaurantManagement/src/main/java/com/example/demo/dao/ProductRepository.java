package com.example.demo.dao;

import java.util.List;

<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
=======
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
>>>>>>> 8f4859ad739749e36073266c8243523d11bc0a09
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.Product;

<<<<<<< HEAD
=======
public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query("select p.code from Product as p where p.code = :newCode")
	public List<String> checkCode(@RequestParam("newCode") String newCode);
	
	@Query("FROM Product p WHERE p.name LIKE %:searchText% "
			+ "OR p.description LIKE %:searchText% "
			+ "OR p.code LIKE %:searchText% "
			+ "OR p.price LIKE %:searchText% ORDER BY p.name ASC")
    Page<Product> findAllProducts(Pageable pageable, @Param("searchText") String searchText);
	
	@Query("FROM Product p ORDER BY p.name ASC")
	Page<Product> findAllProductsAscending(Pageable pageable);
>>>>>>> 8f4859ad739749e36073266c8243523d11bc0a09

public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query("select code from Product where code =: newCode")
	public List<String> checkCode(@RequestParam("code") String newCode);
}
