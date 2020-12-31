package com.example.demo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Bill;

public interface BillRepository extends JpaRepository<Bill, Long>{
	@Query(value = "SELECT b.id, b.payment_date,b.payment_method,b.employee_id,b.promotion_type_id,b.book_table_id,b.total_price"
			+ " FROM bills b, employees e WHERE b.employee_id=e.id and concat( e.last_name, e.first_name) like %?1%", nativeQuery = true)
	public List<Bill> findBillByName( String key);
	@Query(value = "SELECT * from bills where payment_date = ?1", nativeQuery = true)
	public List<Bill> findBillByDate( String date);
	
	@Query(value = "SELECT * FROM restaurant_management.bills where payment_date is not null", nativeQuery = true)
	public Page<Bill> getAllBill(Pageable pageable);
	
	@Query(value = "SELECT * from bills where payment_date = ?1  ORDER BY  payment_date ASC", nativeQuery = true)
    public Page<Bill> findAllTypesByDate(Pageable pageable, String date);
	@Query(value = "SELECT b.id, b.payment_date,b.payment_method,b.employee_id,b.promotion_type_id,b.book_table_id"
			+ " FROM bills b, employees e WHERE b.employee_id=e.id and concat( e.last_name, e.first_name) like %?1% order by b.payment_date ASC", nativeQuery = true)
	public Page<Bill> findAllBillByName(Pageable pageable, String key);
	
	@Query(value="select sum(BD.price *BD.amount) from restaurant_management.bills B, restaurant_management.bill_details BD " + 
			"where B.id=BD.bill_id and B.id=?1",nativeQuery = true)
	public Long getToTalPriceById(Long BillId);
	@Query(value="Select * from restaurant_management.bills B where B.payment_date is null  and B.book_table_id=?1", nativeQuery = true)
	public Bill getBillByTableId(int tableId);

}
