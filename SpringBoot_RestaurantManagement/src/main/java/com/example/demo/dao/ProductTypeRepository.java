package com.example.demo.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.models.ProductType;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
	@Query("FROM ProductType pt WHERE pt.name LIKE %:searchText% OR pt.description LIKE %:searchText% ORDER BY pt.name ASC")
    Page<ProductType> findAllTypes(Pageable pageable, @Param("searchText") String searchText);
	
	@Query("FROM ProductType pt ORDER BY pt.name ASC")
	Page<ProductType> findAllTypesAscending(Pageable pageable);
}
