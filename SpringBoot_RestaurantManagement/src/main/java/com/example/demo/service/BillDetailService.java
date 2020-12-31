package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.BillDetailRepository;
import com.example.demo.models.BillDetails;
import com.example.demo.models.BillDetailsId;

@Service
@Transactional
public class BillDetailService {
	@Autowired
	private BillDetailRepository billDetailRepository;
	
	
	public List<BillDetails> listAll(){
		return billDetailRepository.findAll();
	}
	
	public BillDetails save(BillDetails billDetails) {
		return billDetailRepository.save(billDetails);
	}
	public List<BillDetails> listAllByBillId(Long billId){
		return billDetailRepository.listAllByBillId(billId);
	}
	public BillDetails get(BillDetailsId id) {
		
		return billDetailRepository.findById(id).get();
	}
	
	public void delete(BillDetailsId id) {
		billDetailRepository.deleteById(id);
	}
	public Optional<BillDetails> findById(Long id, Long id2) {
		return billDetailRepository.findById(new BillDetailsId(id, id2));
	}
//	public void UpdateAmount(Long id, Long id2) {
//		 billDetailRepository.UpdateAmount(id,id2);
//	}
	

}
