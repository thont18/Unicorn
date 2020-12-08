package com.example.demo.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IngredientDetailsId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "ingredientId", nullable = false)
	private Long ingredientId;
	@Column(name = "productId", nullable = false)
	private Long productId;

	public IngredientDetailsId(Long ingredientId, Long productId) {
		super();
		this.ingredientId = ingredientId;
		this.productId = productId;
	}

	public IngredientDetailsId() {
		// TODO Auto-generated constructor stub
	}

	public Long getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(Long ingredientId) {
		this.ingredientId = ingredientId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ingredientId, productId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IngredientDetailsId other = (IngredientDetailsId) obj;
		return Objects.equals(ingredientId, other.ingredientId) && Objects.equals(productId, other.productId);
	}
}
