package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundExeption;
import com.example.demo.models.BookingTable;
import com.example.demo.models.BookingTableStatus;

import com.example.demo.models.WorkingSite;
import com.example.demo.service.BookingTableService;
import com.example.demo.service.WorkingSiteService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/tables")
public class BookingTableController {
	@Autowired
	private BookingTableService bookingTableService;
	@Autowired
	private WorkingSiteService workingSiteService;

	@GetMapping
	public List<BookingTable> getAllBookingTables() {
		return this.bookingTableService.findAll();
	}

	@GetMapping("/{id}")
	public BookingTable getBookingTable(@PathVariable("id") Long id) {
		return this.bookingTableService.get(id)
				.orElseThrow(() -> new ResourceNotFoundExeption("Booking Table ID: " + id + " NOT FOUND"));
	}

	@PostMapping
	public ResponseEntity<?> createBookingTable(@RequestParam("code") String code,
			@RequestParam("status") BookingTableStatus status, @RequestParam("siteId") Long siteId) {
		BookingTable newBookingTable = new BookingTable();
		WorkingSite site = workingSiteService.get(siteId).orElseThrow(() -> new ResourceNotFoundExeption("Site ID: " + siteId + " NOT FOUND"));;
		newBookingTable.setCode(code);
		newBookingTable.setStatus(status);
		newBookingTable.setSite(site);

		BookingTable bookingTable = bookingTableService.save(newBookingTable);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("MyHeader", "MyValue");
		return new ResponseEntity<>(bookingTable, httpHeaders, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateBookingTable(@PathVariable("id") Long id, @RequestParam("code") String code,
			@RequestParam("status") BookingTableStatus status, @RequestParam("siteId") Long siteId) {
		BookingTable existingBookingTable = bookingTableService.get(id)
				.orElseThrow(() -> new ResourceNotFoundExeption("Booking Table ID: " + id + " NOT FOUND"));
		WorkingSite site = workingSiteService.get(siteId).orElseThrow(() -> new ResourceNotFoundExeption("Site ID: " + siteId + " NOT FOUND"));;
		existingBookingTable.setCode(code);
		existingBookingTable.setStatus(status);
		existingBookingTable.setSite(site);

		BookingTable bookingTable = bookingTableService.save(existingBookingTable);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("MyHeader", "MyValue");
		return new ResponseEntity<>(bookingTable, httpHeaders, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public void deleteBookingTable(@PathVariable("id") Long id) {
		BookingTable bookingTable = bookingTableService.get(id)
				.orElseThrow(() -> new ResourceNotFoundExeption("Booking Table ID: " + id + " NOT FOUND"));
		bookingTableService.delete(bookingTable.getId());
	}
	@GetMapping("/search/{name}")
	public List<BookingTable> search(@PathVariable ("code") String code){
		return bookingTableService.seachBookingTable(code);
	}
}