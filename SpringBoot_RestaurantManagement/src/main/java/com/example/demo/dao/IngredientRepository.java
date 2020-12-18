package com.example.demo.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.models.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
	@Query("select i from Ingredient i where i.name like %:name% ")
	Page<Ingredient> searchByName(Pageable pageable, @Param("name") String name);
}
