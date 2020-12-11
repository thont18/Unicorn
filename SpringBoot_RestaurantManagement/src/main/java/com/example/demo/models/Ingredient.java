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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@JsonIgnore
	@OneToMany(mappedBy = "ingredient", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<IngredientDetails> ingredientDetails;

	public Ingredient(Long id, String name, String unit, List<IngredientDetails> ingredientDetails) {
		super();
		this.id = id;
		this.name = name;
		this.unit = unit;
		this.ingredientDetails = ingredientDetails;
	}

	public Ingredient(String name, String unit, List<IngredientDetails> ingredientDetails) {
		super();
		this.name = name;
		this.unit = unit;
		this.ingredientDetails = ingredientDetails;
	}

	public Ingredient(String name, String unit) {
		super();
		this.name = name;
		this.unit = unit;
	}
	
	public Ingredient() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public List<IngredientDetails> getIngredientDetails() {
		return ingredientDetails;
	}

	public void setIngredientDetails(List<IngredientDetails> ingredientDetails) {
		this.ingredientDetails = ingredientDetails;
	}

}
