package com.example.demo.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "PRODUCTS")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 100, nullable = false, unique = true)
	private String code;
	@Column(length = 100, nullable = false)
	private String name;
	@Column(length = 30, nullable = false)
	private String unit;
	@Column(length = 10, nullable = false)
	private Double price;
	@Column(nullable = false)
	private ProductStatus status;
	@Column(columnDefinition = "TEXT")
	private String image;
	@Column(length = 200)
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_type_id")
	private ProductType productType;

	@JsonBackReference(value = "product")
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<BillDetails> billDetails;

	@JsonBackReference(value = "product")
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<IngredientDetails> ingredientDetails;

	public Product(Long id, String code, String name, String unit, Double price, ProductStatus status, String image,
			String description, ProductType productType, List<BillDetails> billDetails,
			List<IngredientDetails> ingredientDetails) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.unit = unit;
		this.price = price;
		this.status = status;
		this.image = image;
		this.description = description;
		this.productType = productType;
		this.billDetails = billDetails;
		this.ingredientDetails = ingredientDetails;
	}

	public Product(String code, String name, String unit, Double price, ProductStatus status, String image,
			String description, ProductType productType, List<BillDetails> billDetails,
			List<IngredientDetails> ingredientDetails) {
		super();
		this.code = code;
		this.name = name;
		this.unit = unit;
		this.price = price;
		this.status = status;
		this.image = image;
		this.description = description;
		this.productType = productType;
		this.billDetails = billDetails;
		this.ingredientDetails = ingredientDetails;
	}

	public Product(String code, String name, String unit, Double price, ProductStatus status, String image,
			String description, ProductType productType) {
		super();
		this.code = code;
		this.name = name;
		this.unit = unit;
		this.price = price;
		this.status = status;
		this.image = image;
		this.description = description;
		this.productType = productType;
	}

	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public ProductStatus getStatus() {
		return status;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public List<BillDetails> getBillDetails() {
		return billDetails;
	}

	public void setBillDetails(List<BillDetails> billDetails) {
		this.billDetails = billDetails;
	}

	public List<IngredientDetails> getIngredientDetails() {
		return ingredientDetails;
	}

	public void setIngredientDetails(List<IngredientDetails> ingredientDetails) {
		this.ingredientDetails = ingredientDetails;
	}
}
