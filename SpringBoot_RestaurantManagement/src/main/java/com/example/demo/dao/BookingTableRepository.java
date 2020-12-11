package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.BookingTable;

public interface BookingTableRepository extends JpaRepository<BookingTable, Long> {

}
