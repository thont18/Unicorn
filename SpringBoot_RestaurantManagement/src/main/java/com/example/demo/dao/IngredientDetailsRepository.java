package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.IngredientDetails;
import com.example.demo.models.IngredientDetailsId;

public interface IngredientDetailsRepository extends JpaRepository<IngredientDetails, IngredientDetailsId> {

}
