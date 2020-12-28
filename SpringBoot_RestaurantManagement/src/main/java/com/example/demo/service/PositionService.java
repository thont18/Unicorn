package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PositionRepository;
import com.example.demo.models.Employee;
import com.example.demo.models.Position;

@Service
public class PositionService {

	@Autowired
	private PositionRepository posRepo;

	public List<Position> listAll() {
		return posRepo.findAll();
	}

	public Position save(Position position) {
		return posRepo.save(position);
	}

	public Optional<Position> get(Long id) {
		return posRepo.findById(id);
	}

	public void delete(Long id) {
		posRepo.deleteById(id);
	}
	
	public Page<Position> listAll(Pageable pageable, String searchName) {
		return posRepo.findAllPosition(pageable, searchName);
	}

	public Page<Position> findAll(Pageable pageable) {
		return posRepo.findAll(pageable);
	}

}
