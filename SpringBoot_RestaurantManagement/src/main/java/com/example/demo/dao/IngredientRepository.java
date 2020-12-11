package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
