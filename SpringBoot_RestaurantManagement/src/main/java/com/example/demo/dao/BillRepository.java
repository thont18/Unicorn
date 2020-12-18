package com.example.demo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Bill;

public interface BillRepository extends JpaRepository<Bill, Long>{
	@Query(value = "SELECT b.id, b.payment_date,b.payment_method,b.employee_id,b.promotion_type_id,b.book_table_id"
			+ " FROM bills b, employees e WHERE b.employee_id=e.id and concat( e.last_name, e.first_name) like %?1%", nativeQuery = true)
	public List<Bill> findBillByName( String key);
	@Query(value = "SELECT * from bills where payment_date = ?1", nativeQuery = true)
	public List<Bill> findBillByDate( String date);
	
	@Query(value = "SELECT * from bills where payment_date = ?1  ORDER BY payment_date ASC", nativeQuery = true)
//	@Query("FROM Bill pt WHERE pt.paymentDate LIKE %:date%  ORDER BY pt.paymentDate ASC")
    public Page<Bill> findAllTypesByDate(Pageable pageable, String date);
	@Query(value = "SELECT b.id, b.payment_date,b.payment_method,b.employee_id,b.promotion_type_id,b.book_table_id"
			+ " FROM bills b, employees e WHERE b.employee_id=e.id and concat( e.last_name, e.first_name) like %?1% order by b.payment_date ASC", nativeQuery = true)
	public Page<Bill> findAllBillByName(Pageable pageable, String key);

}
