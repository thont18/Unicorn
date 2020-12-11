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

@Entity
@Table(name = "PRODUCT_TYPES")
public class ProductType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 100, nullable = false)
	private String name;
	@Column
	private String description;

	@JsonBackReference
	@OneToMany(mappedBy = "productType", cascade = CascadeType.ALL)
	private List<Product> products;

	public ProductType(Long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public ProductType(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public ProductType(Long id, String name, String description, List<Product> products) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.products = products;
	}

	public ProductType() {

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
