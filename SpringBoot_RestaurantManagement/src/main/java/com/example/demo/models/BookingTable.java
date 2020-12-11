package com.example.demo.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "BOOKING_TABLES")
public class BookingTable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 30)
	private String code;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private BookingTableStatus status;

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "site_id")
	private WorkingSite site;
	
	@JsonBackReference
	@OneToMany(mappedBy = "table", cascade = CascadeType.ALL)
	private List<Bill> bills;
	
	public BookingTable() {
		// TODO Auto-generated constructor stub
	}

	public BookingTable(String code, BookingTableStatus status, WorkingSite site) {
		super();
		this.code = code;
		this.status = status;
		this.site = site;
	}

	public BookingTable(String code, BookingTableStatus status, WorkingSite site, List<Bill> bills) {
		super();
		this.code = code;
		this.status = status;
		this.site = site;
		this.bills = bills;
	}

	public BookingTable(Long id, String code, BookingTableStatus status, WorkingSite site, List<Bill> bills) {
		super();
		this.id = id;
		this.code = code;
		this.status = status;
		this.site = site;
		this.bills = bills;
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

	public BookingTableStatus getStatus() {
		return status;
	}

	public void setStatus(BookingTableStatus status) {
		this.status = status;
	}

	public WorkingSite getSite() {
		return site;
	}

	public void setSite(WorkingSite site) {
		this.site = site;
	}

	public List<Bill> getBills() {
		return bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}

}
