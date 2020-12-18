package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query("select code from Product where code =: newCode")
	public List<String> checkCode(@RequestParam("code") String newCode);
}
