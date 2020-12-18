package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Ingredient;
import com.example.demo.service.IngredientService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class IngredientController {
	@Autowired
	private IngredientService ingSer;

	@GetMapping("/ingredients")
	public List<Ingredient> getAllIngredient() {
		return this.ingSer.listAll();

	}

	@GetMapping("/ingredients/{id}")
	public Ingredient getIngredient(@PathVariable("id") Long id) {
		return this.ingSer.get(id);
	}

	@PostMapping("/ingredients")
	public Ingredient createIngredient(@RequestBody Ingredient ingredient) {
		return ingSer.save(ingredient);
	}

	@PutMapping("/ingredients")
	public Ingredient updateIngredient(@RequestBody @Validated Ingredient ingredient, @RequestParam Long id) {
		Ingredient anIngredient = ingSer.get(id);
		anIngredient.setName(ingredient.getName());
		anIngredient.setUnit(ingredient.getUnit());
		return ingSer.save(anIngredient);
	}

	@DeleteMapping("/ingredients/{id}")
	public void deleteIngredient(@PathVariable("id") Long id) {
		this.ingSer.delete(id);
	}
	@GetMapping("ingredients/search/{name}")
	public List<Ingredient> search(@PathVariable ("name") String name){
		return ingSer.seachIngredient(name);
	}

}
