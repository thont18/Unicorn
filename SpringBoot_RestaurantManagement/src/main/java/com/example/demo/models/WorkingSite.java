package com.example.demo.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "WORKING_SITES")
public class WorkingSite {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 100)
	private String name;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private WorkingSiteStatus status;
	
	@OneToMany(mappedBy = "site", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Employee> employees;
	
	@OneToMany(mappedBy = "site", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<BookingTable> tables;
}
