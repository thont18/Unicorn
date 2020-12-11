package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.BillDetails;
import com.example.demo.models.BillDetailsId;

public interface BillDetailRepository extends JpaRepository<BillDetails, BillDetailsId> {

}
