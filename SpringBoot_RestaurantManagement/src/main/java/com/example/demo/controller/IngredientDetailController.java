package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exeption.ResourceNotFoundExeption;
import com.example.demo.models.Ingredient;
import com.example.demo.models.IngredientDetails;
import com.example.demo.models.IngredientDetailsId;
import com.example.demo.models.Product;
import com.example.demo.service.IngredientDetailsService;
import com.example.demo.service.IngredientService;


@RestController
@CrossOrigin(origins = "http://localhost:3000") 
public class IngredientDetailController {
	@Autowired
	private IngredientDetailsService ingdSer;
	

	@GetMapping("/ingredientdetails")
	public List<IngredientDetails> getAllIngredientDetails() {
		return this.ingdSer.listAll();
	}

	@GetMapping("/ingredientdetails/{ingredientId}_{productId}")
	public Optional<IngredientDetails> getIngredientDetails(@PathVariable Long ingredientId,@PathVariable Long productId ) {
		return this.ingdSer.findByid(ingredientId, productId);
	}
	
	@PostMapping("/ingredientdetails/{ingredientId}_{productId}")
	public IngredientDetails createIngredientDetails(@RequestBody IngredientDetails ingredientDetails,@PathVariable Long ingredientId,@PathVariable Long productId ) {
		return ingdSer.save(ingredientDetails);
	}
	@PutMapping ("/ingredientdetails/{ingredientId}_{productId}")
	public ResponseEntity <IngredientDetails> updateIngredientDetails (@RequestBody IngredientDetails ingredientDetails,@PathVariable Long ingredientId,@PathVariable Long productId  ){
		IngredientDetails ingre=ingdSer.findByid(ingredientId, productId).orElseThrow(()->
		new ResourceNotFoundExeption("IngredientDetails not exist"));
		ingre.setAmount(ingredientDetails.getAmount());
		return ResponseEntity.ok(ingdSer.save(ingre));
		
		
	}
	@DeleteMapping ("/ingredientdetails/{ingredientId}_{productId}")
	public Map<String,Boolean> deleteIngredientdetails(@PathVariable Long ingredientId,@PathVariable Long productId){
		IngredientDetails ingDt=ingdSer.findByid(ingredientId, productId).orElseThrow(()->
		new ResourceNotFoundExeption("IngredientDetails not exist"));
		IngredientDetailsId ingre= new IngredientDetailsId(ingDt.getIngredient().getId(),ingDt.getProduct().getId());
		ingdSer.delete(ingre);
		Map<String,Boolean> response= new HashMap<String, Boolean>();
		response.put("Deleted", Boolean.TRUE);
		return response;
		
		
		
		
				}
	
	

}
