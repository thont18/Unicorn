package com.example.demo.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "BILL_DETAILS")
public class BillDetails implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BillDetailsId id = new BillDetailsId();

	@ManyToOne(optional = false)
	@MapsId("billId")
	private Bill bill;

	@ManyToOne(optional = false)
	@MapsId("productId")
	private Product product;

	public BillDetails() {
		// TODO Auto-generated constructor stub
	}

	@Column(nullable = false, length = 10)
	private Integer amount;
	@Column(nullable = false, length = 10)
	private Double price;
	@Column(nullable = false, length = 10)
	private Integer finishedProductNum;
	@Temporal(TemporalType.DATE)
	private Date orderDate;
	@Temporal(TemporalType.DATE)
	private Date finishedProductDate;

	public BillDetailsId getId() {
		return id;
	}

	public void setId(BillDetailsId id) {
		this.id = id;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getFinishedProductNum() {
		return finishedProductNum;
	}

	public void setFinishedProductNum(Integer finishedProductNum) {
		this.finishedProductNum = finishedProductNum;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getFinishedProductDate() {
		return finishedProductDate;
	}

	public void setFinishedProductDate(Date finishedProductDate) {
		this.finishedProductDate = finishedProductDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bill, product);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BillDetails other = (BillDetails) obj;
		return Objects.equals(bill, other.bill) && Objects.equals(product, other.product);
	}
}
