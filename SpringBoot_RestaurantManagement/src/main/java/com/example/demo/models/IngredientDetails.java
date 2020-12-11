package com.example.demo.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "INGREDIENT_DETAILS")
public class IngredientDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private IngredientDetailsId id = new IngredientDetailsId();

	@ManyToOne
	@MapsId("ingredientId")
	private Ingredient ingredient;

	@ManyToOne
	@MapsId("productId")
	private Product product;

	@Column(length = 10, nullable = false)
	private Integer amount;

	public IngredientDetails() {
		// TODO Auto-generated constructor stub
	}

	public IngredientDetails(IngredientDetailsId id, Ingredient ingredient, Product product, Integer amount) {
		super();
		this.id = id;
		this.ingredient = ingredient;
		this.product = product;
		this.amount = amount;
	}

	public IngredientDetails(Ingredient ingredient, Product product, Integer amount) {
		super();
		this.ingredient = ingredient;
		this.product = product;
		this.amount = amount;
	}

	public IngredientDetailsId getId() {
		return id;
	}

	public void setId(IngredientDetailsId id) {
		this.id = id;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ingredient, product);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IngredientDetails other = (IngredientDetails) obj;
		return Objects.equals(ingredient, other.ingredient) && Objects.equals(product, other.product);
	}

}
