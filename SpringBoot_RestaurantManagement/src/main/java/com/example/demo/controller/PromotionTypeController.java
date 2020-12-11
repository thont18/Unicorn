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
import com.example.demo.models.PromotionType;
import com.example.demo.service.PromotionTypeService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PromotionTypeController {
	@Autowired
	private PromotionTypeService proSer;

	@GetMapping("/promotiontypes")
	public List<PromotionType> getAllPromotionType() {
		return this.proSer.listAll();

	}
	@GetMapping("/promotiontypes/{id}")
	public PromotionType getPromotionType(@PathVariable("id") Long id) {
		return this.proSer.get(id);
	}
	@PostMapping("/promotiontypes")
	public PromotionType createPromotionType(@RequestBody PromotionType promotionType) {
		return proSer.save(promotionType);
	} 
	@PutMapping("/promotiontypes")
	public PromotionType updatePromotionType(@RequestBody @Validated PromotionType promotionType, @RequestParam Long id) {
		PromotionType anPromotionType = proSer.get(id);
		anPromotionType.setName(promotionType.getName());
		//anIngredient.setUnit(ingredient.getUnit());
		return proSer.save(anPromotionType);
	}
	@DeleteMapping("/promotiontypes/{id}")
	public void deletePromotionType(@PathVariable("id") Long id) {
		this.proSer.delete(id);
	}


}
