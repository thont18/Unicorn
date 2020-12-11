package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IngredientDetailsRepository;
import com.example.demo.models.IngredientDetails;
import com.example.demo.models.IngredientDetailsId;

@Service
@Transactional

public class IngredientDetailsService {
	@Autowired
	private IngredientDetailsRepository in;

	public List<IngredientDetails> listAll() {
		return in.findAll();

	}

	public IngredientDetails save(IngredientDetails ingredientDetails) {
		return in.save(ingredientDetails);
	}

	public IngredientDetails get(IngredientDetailsId id) {
		return in.findById(id).get();
	}

	public void delete(IngredientDetailsId id) {
		in.deleteById(id);
	}

	public Optional<IngredientDetails> findByid(Long id,Long id2) {
		return in.findById(new IngredientDetailsId(id,id2) );
	}

}
