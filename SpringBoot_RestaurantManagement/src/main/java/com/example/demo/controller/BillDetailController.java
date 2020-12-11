package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exeption.ResourceNotFoundExeption;
import com.example.demo.models.Bill;
import com.example.demo.models.BillDetails;
import com.example.demo.models.BillDetailsId;
import com.example.demo.service.BillDetailService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api")
public class BillDetailController {
	@Autowired
	private BillDetailService bds;
	
	@GetMapping("/billDetail")
	public List<BillDetails> getBillDetail(){
		return this.bds.listAll();
	}
	@GetMapping("/billDetail/{billId}_{producId}")
	public Optional<BillDetails> getBillDetailsById(@PathVariable Long billId,@PathVariable Long producId) {
		return this.bds.findById(billId,producId);
	}
	@PostMapping("/billDetail/{billId}_{producId}")
	public BillDetails createBillDetail(@RequestBody BillDetails billDetails,@PathVariable Long billId,@PathVariable Long producId) {
		return bds.save(billDetails);
	}
	@PutMapping("/billDetail/{billId}_{producId}")
	 public ResponseEntity<BillDetails> updateBillDetails(@RequestBody BillDetails billDetails, @PathVariable Long billId,@PathVariable Long producId) {
		BillDetails billDt=bds.findById(billId,producId).orElseThrow(()->
		new ResourceNotFoundExeption("Billdetail not exist"));  
		billDt.setAmount(billDetails.getAmount());
		billDt.setFinishedProductDate(billDetails.getFinishedProductDate());
		billDt.setFinishedProductNum(billDetails.getFinishedProductNum());
		billDt.setOrderDate(billDetails.getOrderDate());
		billDt.setPrice(billDetails.getPrice());
		return ResponseEntity.ok(bds.save(billDt));
		
	}
	
	@DeleteMapping("/billDetail/{billId}_{productId}")
	 public  Map<String,Boolean> deleteBill(@PathVariable Long billId,@PathVariable Long productId) {
		BillDetails billDt=bds.findById(billId,productId).orElseThrow(()->
		new ResourceNotFoundExeption("Billdetail not exist"));  
		BillDetailsId billDetailsId=new BillDetailsId(billDt.getBill().getId(),billDt.getProduct().getId());
		bds.delete(billDetailsId);
		Map<String,Boolean> response=new HashMap<String, Boolean>();
		response.put("Deleted", Boolean.TRUE);
		return response;
	}
	
	
	

}
