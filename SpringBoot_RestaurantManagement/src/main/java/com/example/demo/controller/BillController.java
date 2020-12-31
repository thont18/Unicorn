package com.example.demo.controller;

import java.io.Console;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
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
import com.example.demo.models.BookingTable;
import com.example.demo.models.Employee;
import com.example.demo.models.PaymentMethod;
import com.example.demo.models.ProductType;
import com.example.demo.models.PromotionType;
import com.example.demo.service.BillService;
import com.example.demo.service.BookingTableService;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.PromotionTypeService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/bills")
public class BillController {
	@Autowired
	BillService billService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	BookingTableService btService;
	@Autowired
	PromotionTypeService promoService;

	@GetMapping("/getBill/{tableId}")
	public Bill getBillByTableId(@PathVariable int tableId) {
		return billService.getBillByTableId(tableId);
	}

	@GetMapping()
	public ResponseEntity<Page<Bill>> getALLBills(int pageNumber, int pageSize, String sortBy, String sortDir) {

		return new ResponseEntity<Page<Bill>>(
				billService.findAll(PageRequest.of(pageNumber, pageSize,
						sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending())),
				HttpStatus.OK);
	}

	@GetMapping("/getToTalPrice/{id}")
	public Long getTotalPriceByID(@PathVariable Long id) {
		Bill bill = billService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exists with id= " + id));
		return billService.getToTalPriceById(id);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Bill> getBillByID(@PathVariable Long id) {
		Bill bill = billService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exists with id= " + id));
		return ResponseEntity.ok(bill);
	}

	@GetMapping("/findDate")
	public ResponseEntity<Page<Bill>> getBillByDate(Pageable pageable, @RequestParam String date) {
		// return this.billService.findBillByDate(date);
		return new ResponseEntity<>(billService.listAllByDate(pageable, date), HttpStatus.OK);
	}

	@GetMapping("/find")
	public ResponseEntity<Page<Bill>> getBillByName(Pageable pageable, @RequestParam String name) {
		return new ResponseEntity<>(billService.listAllByName(pageable, name), HttpStatus.OK);
	}

	@PostMapping(consumes = "multipart/form-data")
	public ResponseEntity<Bill> createBill(/* @RequestParam("paymentDate") Date paymentDate, */
			/* @RequestParam("paymentMethod") PaymentMethod paymentMethod, */ @RequestParam("tableId") Long tableId
	/*
	 * @RequestParam("employeeId") Long employeeId, @RequestParam("promotionId")
	 * Long promotionId
	 */) {

		// Employee employee = employeeService.get(employeeId);
		// PromotionType promotionType = promoService.get(promotionId);
		Optional<BookingTable> bookingTable = btService.get(tableId);

		Bill bill = new Bill();
		// bill.setPaymentDate(paymentDate);
		// bill.setPaymentMethod(paymentMethod);
		// bill.setEmployee(employee);
		// bill.setPromotionType(promotionType);
		bill.setTable(bookingTable.get());

		return ResponseEntity.ok(this.billService.save(bill));
	}

	@SuppressWarnings("unused")
	@PutMapping(value = "/{id}", consumes = "multipart/form-data")
	public ResponseEntity<Bill> updateBill(@PathVariable Long id, @Param("paymentDate") Date paymentDate,
			@Param("paymentMethod") PaymentMethod paymentMethod, @Param("tableId") Long tableId,
			@Param("employeeId") Long employeeId, @Param("promotionId") Long promotionId,@Param("totalPrice") double totalPrice) {
		Employee employee = null;
		PromotionType promotionType = null;
		Optional<BookingTable> bookingTable = null;
		if (employeeId != null) {
			employee = employeeService.get(employeeId);
		}
		if (promotionId != null) {
			promotionType = promoService.get(promotionId);
		}
		if (bookingTable != null) {
			bookingTable = btService.get(tableId);
		}

		Bill bill = billService.get(id);
		bill.setPaymentDate(paymentDate);
		bill.setPaymentMethod(paymentMethod);
		bill.setTotalPrice(totalPrice);
		if (employeeId != null) {
			bill.setEmployee(employee);
		}
		if (promotionId != null) {
			bill.setPromotionType(promotionType);
		}
		if (bookingTable != null) {
			bill.setTable(bookingTable.get());
		}

		return ResponseEntity.ok(this.billService.save(bill));
	}

	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteBill(@PathVariable Long id) {
		Bill bill = billService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exists with id= " + id));
		billService.delete(id);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("Deleted", Boolean.TRUE);
		return response;
	}

//	@PutMapping("/{id}")
//	public ResponseEntity<Bill> updateBill(@RequestBody Bill bill, @PathVariable Long id) {
//		Bill ebill = billService.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Employee not exists with id= " + id));
//		ebill.setBillDetails(bill.getBillDetails());
//		ebill.setEmployee(bill.getEmployee());
//		ebill.setPaymentDate(bill.getPaymentDate());
//		ebill.setPaymentMethod(bill.getPaymentMethod());
//		ebill.setPromotionType(bill.getPromotionType());
//		ebill.setTable(bill.getTable());
//		return ResponseEntity.ok(billService.save(ebill));
//	}
}
