package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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

	public Ingredient save(Ingredient ingredient) {
		return repo.save(ingredient);
	}

	public Ingredient get(Long id) {
		return repo.findById(id).get();
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}
	public List<Ingredient> seachIngredient(@PathVariable("name") String name){
		return repo.search(name);
	}

}
