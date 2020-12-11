package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Bill;

public interface BillRepository extends JpaRepository<Bill, Long>{

}
