package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public Page<Bill> listAllByDate(Pageable pageable, String searchText) {
		return billRepo.findAllTypesByDate(pageable, searchText);
	}
	public Page<Bill> listAllByName(Pageable pageable, String searchText) {
		return billRepo.findAllBillByName(pageable, searchText);
	}
	public Page<Bill> findAll(Pageable pageable) {
		return billRepo.findAll(pageable);
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
	public List<Bill> findBillByName(String name){
		List<Bill> list=billRepo.findBillByName(name);
		return list;
	}	
	public List<Bill> findBillByDate(String date){
		List<Bill> list=billRepo.findBillByDate(date);
		return list;
	}	
	

}
