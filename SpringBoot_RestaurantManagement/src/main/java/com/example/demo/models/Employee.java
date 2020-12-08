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

@Entity
@Table(name = "EMPLOYEES")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 50)
	private String lastName;
	@Column(nullable = false, length = 50)
	private String firstName;
	@Column(nullable = false, length = 20)
	private String phoneNumber;
	@Column(length = 200)
	private String address;
	@Column(length = 20, nullable = false, unique = true)
	private String identityCardNumber; 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "position_id")
	private Position position;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "site_id")
	private WorkingSite site;
	
	@OneToMany(mappedBy = "employee", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Bill> bills;
}
