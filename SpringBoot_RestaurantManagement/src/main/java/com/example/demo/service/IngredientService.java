package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IngredientRepository;
import com.example.demo.models.Ingredient;

@Service
@Transactional

public class IngredientService {
	@Autowired
	private IngredientRepository repo;

	public List<Ingredient> listAll() {
		return repo.findAll();

	}
	
	public Page<Ingredient> listAll(Pageable pageable) {
		return repo.findAll(pageable);
	}

	public Ingredient save(Ingredient ingredient) {
		return repo.save(ingredient);
	}

	public Ingredient get(Long id) {
		return repo.findById(id).get();
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}

	public Page<Ingredient> seachIngredients(Pageable pageable,  String name){
		return repo.searchByName(pageable, name);
	}

}
