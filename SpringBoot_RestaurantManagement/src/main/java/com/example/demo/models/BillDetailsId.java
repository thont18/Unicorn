package com.example.demo.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BillDetailsId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "billId", nullable = false)
	private Long billId;
	@Column(name = "productId", nullable = false)
	private Long productId;

	public BillDetailsId(Long billId, Long productId) {
		super();
		this.billId = billId;
		this.productId = productId;
	}

	public BillDetailsId() {
		// TODO Auto-generated constructor stub
	}

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(billId, productId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BillDetailsId other = (BillDetailsId) obj;
		return Objects.equals(billId, other.billId) && Objects.equals(productId, other.productId);
	}
}
