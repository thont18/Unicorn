package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.BillDetails;
import com.example.demo.models.BillDetailsId;

public interface BillDetailRepository extends JpaRepository<BillDetails, BillDetailsId> {
	@Query(value="Select * from bill_details where bill_id=?1", nativeQuery = true)
	public List<BillDetails> listAllByBillId(Long billId);
//	@Query(value="Update restaurant_management.bill_details set amount=amount+1 where bill_id=?1 and product_id=?2", nativeQuery = true)
//	public void UpdateAmount(Long billId,Long ProId);

}
