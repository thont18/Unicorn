package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

//	@GetMapping("/ingredients")
//	public List<Ingredient> getAllIngredient() {
//		return this.ingSer.listAll();
//
//	}

	@GetMapping("/ingredients")
	public ResponseEntity<Page<Ingredient>> getIngredients(int pageNumber, int pageSize, String sortBy,
			String sortDir) {
//		return new ResponseEntity<>(ser.findAll(pageable), HttpStatus.OK);
		return new ResponseEntity<Page<Ingredient>>(
				ingSer.listAll(PageRequest.of(pageNumber, pageSize,
						sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending())),
				HttpStatus.OK);
	}

	@GetMapping("/ingredients/search/{name}")
	public ResponseEntity<Page<Ingredient>> searchIngredients(Pageable pageable, @PathVariable String name) {
		return new ResponseEntity<>(ingSer.seachIngredients(pageable, name), HttpStatus.OK);
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

}
