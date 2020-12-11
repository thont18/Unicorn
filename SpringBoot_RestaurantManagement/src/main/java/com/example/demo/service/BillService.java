package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.BillRepository;
import com.example.demo.models.Bill;

@Service
@Transactional
public class BillService {
	@Autowired
	private BillRepository billRepo;

	public List<Bill> listAll() {
		return billRepo.findAll();
	}

	public Bill save(Bill bill) {
		return billRepo.save(bill);
	}

	public Bill get(Long id) {
		return billRepo.findById(id).get();
	}

	public void delete(Long id) {
		 billRepo.deleteById(id);
	}
	public Optional<Bill> findById(Long id){
		return billRepo.findById(id);
	}
	

}
