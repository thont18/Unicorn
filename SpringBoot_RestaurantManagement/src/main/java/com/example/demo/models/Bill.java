package com.example.demo.models;

import java.util.Date;
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
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonValue;

@Entity
@Table(name = "BILLS")
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Temporal(TemporalType.DATE)
	private Date paymentDate;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private PaymentMethod paymentMethod;
	
	@JsonBackReference(value = "bill1")
	@OneToMany(mappedBy = "bill",orphanRemoval = true, cascade = CascadeType.ALL)
	private List<BillDetails> billDetails;
	

	@ManyToOne(/*fetch = FetchType.LAZY ,optional=false*/)
	@JoinColumn(name = "book_table_id")
	private BookingTable table;
	
	private double totalPrice;
	

	
	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Bill(Long id, Date paymentDate, PaymentMethod paymentMethod, List<BillDetails> billDetails,
			BookingTable table, double totalPrice, Employee employee, PromotionType promotionType) {
		super();
		this.id = id;
		this.paymentDate = paymentDate;
		this.paymentMethod = paymentMethod;
		this.billDetails = billDetails;
		this.table = table;
		this.totalPrice = totalPrice;
		this.employee = employee;
		this.promotionType = promotionType;
	}

	public Bill(Long id, BookingTable table) {
		super();
		this.id = id;
		this.table = table;
	}

	public Bill(Date paymentDate, PaymentMethod paymentMethod, List<BillDetails> billDetails, BookingTable table,
			double totalPrice, Employee employee, PromotionType promotionType) {
		super();
		this.paymentDate = paymentDate;
		this.paymentMethod = paymentMethod;
		this.billDetails = billDetails;
		this.table = table;
		this.totalPrice = totalPrice;
		this.employee = employee;
		this.promotionType = promotionType;
	}

	@ManyToOne(/*fetch = FetchType.LAZY ,optional=false*/)
	@JoinColumn(name = "employee_id")

	private Employee employee;
	
	
	@ManyToOne(/* fetch = FetchType.LAZY */)
	@JoinColumn(name = "promotion_type_id")
	private PromotionType promotionType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public List<BillDetails> getBillDetails() {
		return billDetails;
	}

	public void setBillDetails(List<BillDetails> billDetails) {
		this.billDetails = billDetails;
	}

	public BookingTable getTable() {
		return table;
	}

	public void setTable(BookingTable table) {
		this.table = table;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public PromotionType getPromotionType() {
		return promotionType;
	}

	public void setPromotionType(PromotionType promotionType) {
		this.promotionType = promotionType;
	}

	public Bill(Date paymentDate, PaymentMethod paymentMethod, List<BillDetails> billDetails, BookingTable table,
			Employee employee, PromotionType promotionType) {
		super();
		this.paymentDate = paymentDate;
		this.paymentMethod = paymentMethod;
		this.billDetails = billDetails;
		this.table = table;
		this.employee = employee;
		this.promotionType = promotionType;
	}

	public Bill() {
		super();
	}

	public Bill(Long id, Date paymentDate, PaymentMethod paymentMethod, List<BillDetails> billDetails,
			BookingTable table) {
		super();
		this.id = id;
		this.paymentDate = paymentDate;
		this.paymentMethod = paymentMethod;
		this.billDetails = billDetails;
		this.table = table;
	}

	public Bill(Long id, Date paymentDate, PaymentMethod paymentMethod, List<BillDetails> billDetails,
			BookingTable table, Employee employee, PromotionType promotionType) {
		super();
		this.id = id;
		this.paymentDate = paymentDate;
		this.paymentMethod = paymentMethod;
		this.billDetails = billDetails;
		this.table = table;
		this.employee = employee;
		this.promotionType = promotionType;
	}
	
	
}

