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
@Table(name = "PROMOTION_TYPES")
public class PromotionType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 100)
	private String name;
	
	@JsonBackReference
	@OneToMany(mappedBy = "promotionType", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Bill> bills;

	public PromotionType(Long id, String name, List<Bill> bills) {
		super();
		this.id = id;
		this.name = name;
		this.bills = bills;
	}

	public PromotionType(String name, List<Bill> bills) {
		super();
		this.name = name;
		this.bills = bills;
	}

	public PromotionType() {
		
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

	public List<Bill> getBills() {
		return bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}
	
}
