package com.example.demo.dao;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.models.BookingTable;



public interface BookingTableRepository extends JpaRepository<BookingTable, Long> {
	@Query("select i from BookingTable i where i.code like %:code% ")
	Page<BookingTable> searchByCode(Pageable pageable, @Param("code") String code);
	 
}