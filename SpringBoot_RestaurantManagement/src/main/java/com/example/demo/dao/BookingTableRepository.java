package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.BookingTable;


public interface BookingTableRepository extends JpaRepository<BookingTable, Long> {
	@Query("select i from BookingTable i where i.code like %?1% ")
	  public List<BookingTable>search(String code);
}
