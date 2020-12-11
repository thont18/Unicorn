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

import com.fasterxml.jackson.annotation.JsonBackReference;

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
	@JsonBackReference(value = "site1")
	@OneToMany(mappedBy = "site", cascade = CascadeType.ALL)
	private List<Employee> employees;
	@JsonBackReference(value = "site2")
	@OneToMany(mappedBy = "site", cascade = CascadeType.ALL)
	private List<BookingTable> tables;

	public WorkingSite(Long id, String name, WorkingSiteStatus status, List<Employee> employees,
			List<BookingTable> tables) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.employees = employees;
		this.tables = tables;
	}

	public WorkingSite(Long id, String name, WorkingSiteStatus status) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
	}

	public WorkingSite(String name, WorkingSiteStatus status) {
		super();
		this.name = name;
		this.status = status;
	}

	public WorkingSite() {
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

	public WorkingSiteStatus getStatus() {
		return status;
	}

	public void setStatus(WorkingSiteStatus status) {
		this.status = status;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public List<BookingTable> getTables() {
		return tables;
	}

	public void setTables(List<BookingTable> tables) {
		this.tables = tables;
	}

}
