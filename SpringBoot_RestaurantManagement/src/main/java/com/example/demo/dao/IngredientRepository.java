package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
  @Query("select i from Ingredient i where i.name like %?1% ")
  public List<Ingredient>search(String name);
}
