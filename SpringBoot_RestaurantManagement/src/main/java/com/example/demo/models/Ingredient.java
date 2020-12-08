package com.example.demo.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "INGREDIENTS")
public class Ingredient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 50, nullable = false)
	private String name;
	@Column(length = 30, nullable = false)
	private String unit;
	
	@OneToMany(mappedBy = "ingredient", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<IngredientDetails> ingredientDetails;
}
