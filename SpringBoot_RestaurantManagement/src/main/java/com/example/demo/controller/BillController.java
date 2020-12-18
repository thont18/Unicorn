package com.example.demo.controller;

import java.io.Console;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.models.Bill;
import com.example.demo.models.ProductType;
import com.example.demo.service.BillService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/bills")
public class BillController {
	@Autowired
	BillService billService;
	@GetMapping()
	public ResponseEntity<Page<Bill>> getALLBills(int pageNumber, int pageSize, String sortBy,
			String sortDir){
		return new ResponseEntity<Page<Bill>>(
				billService.findAll(PageRequest.of(pageNumber, pageSize,
						sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending())),
				HttpStatus.OK);
		//return this.billService.listAll();
	}
	@GetMapping("/{id}")
	public ResponseEntity<Bill> getBillByID(@PathVariable Long id) {
		Bill bill=billService.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Employee not exists with id= "+id));
		return ResponseEntity.ok(bill);
	}
	@GetMapping("/find")
	public ResponseEntity<Page<Bill>> getBillByName(Pageable pageable,@RequestParam String name){
		return new ResponseEntity<>(billService.listAllByName(pageable, name), HttpStatus.OK);
	}
	@GetMapping("/findDate")
	public ResponseEntity<Page<Bill>> getBillByDate(Pageable pageable,@RequestParam String date){
		//return this.billService.findBillByDate(date);
		return new ResponseEntity<>(billService.listAllByDate(pageable, date), HttpStatus.OK);
	}
	@PostMapping()
	public Bill createBill(@RequestBody Bill bill){
		
		return billService.save(bill);
	}
    @DeleteMapping("/{id}")
    public  Map<String,Boolean> deleteBill(@PathVariable Long id) {
    	Bill bill=billService.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Employee not exists with id= "+id));    	
    	billService.delete(id);
    	Map<String,Boolean> response=new HashMap<String, Boolean>();
		response.put("Deleted", Boolean.TRUE);
		return response;
    }
    @PutMapping("/{id}")
    public ResponseEntity<Bill> updateBill(@RequestBody Bill bill, @PathVariable Long id) {
    	Bill ebill=billService.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Employee not exists with id= "+id));  
    	ebill.setBillDetails(bill.getBillDetails());
    	ebill.setEmployee(bill.getEmployee());
    	ebill.setPaymentDate(bill.getPaymentDate());
    	ebill.setPaymentMethod(bill.getPaymentMethod());
    	ebill.setPromotionType(bill.getPromotionType());
    	ebill.setTable(bill.getTable());
    	return ResponseEntity.ok(billService.save(ebill));
    }
}
