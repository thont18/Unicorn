package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundExeption;
import com.example.demo.models.Bill;
import com.example.demo.service.BillService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api")
public class BillController {
	@Autowired
	BillService billService;
	@GetMapping("/bills")
	public List<Bill> getBills(){
		return this.billService.listAll();
	}
	@GetMapping("/bills/{id}")
	public ResponseEntity<Bill> getBillByID(@PathVariable Long id) {
		Bill bill=billService.findById(id).orElseThrow(()->
		new ResourceNotFoundExeption("Employee not exists with id= "+id));
		return ResponseEntity.ok(bill);
	}
	@PostMapping("/bills")
	public Bill createBill(@RequestBody Bill bill){
		
		return billService.save(bill);
	}
    @DeleteMapping("/bills/{id}")
    public  Map<String,Boolean> deleteBill(@PathVariable Long id) {
    	Bill bill=billService.findById(id).orElseThrow(()->
		new ResourceNotFoundExeption("Employee not exists with id= "+id));    	
    	billService.delete(id);
    	Map<String,Boolean> response=new HashMap<String, Boolean>();
		response.put("Deleted", Boolean.TRUE);
		return response;
    }
    @PutMapping("/bills/{id}")
    public ResponseEntity<Bill> updateBill(@RequestBody Bill bill, @PathVariable Long id) {
    	Bill ebill=billService.findById(id).orElseThrow(()->
		new ResourceNotFoundExeption("Employee not exists with id= "+id));  
    	ebill.setBillDetails(bill.getBillDetails());
    	ebill.setEmployee(bill.getEmployee());
    	ebill.setPaymentDate(bill.getPaymentDate());
    	ebill.setPaymentMethod(bill.getPaymentMethod());
    	ebill.setPromotionType(bill.getPromotionType());
    	ebill.setTable(bill.getTable());
    	return ResponseEntity.ok(billService.save(ebill));
    }
}
