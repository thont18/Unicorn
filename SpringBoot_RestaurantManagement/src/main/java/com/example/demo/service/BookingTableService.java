package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BookingTableRepository;
import com.example.demo.models.BookingTable;

@Service
@Transactional
public class BookingTableService {
	@Autowired
	private BookingTableRepository bookingTableRepository;
	
	public List<BookingTable> findAll() {
		return bookingTableRepository.findAll();
	}
	
	public Optional<BookingTable> get(Long id) {
		return bookingTableRepository.findById(id);
	}
	
	public BookingTable save(BookingTable bookingTable) {
		return bookingTableRepository.save(bookingTable);
	}
	
	public void delete(Long id) {
		bookingTableRepository.deleteById(id);
	}
}
