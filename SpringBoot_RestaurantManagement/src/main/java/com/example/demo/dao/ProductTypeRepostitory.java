package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.ProductType;

public interface ProductTypeRepostitory extends JpaRepository<ProductType, Long>{

}
