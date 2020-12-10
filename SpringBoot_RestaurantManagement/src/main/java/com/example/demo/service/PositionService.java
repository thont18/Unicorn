package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PositionRepository;
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

	public Position get(Long id) {
		return posRepo.findById(id).get();
	}

	public void delete(Long id) {
		posRepo.deleteById(id);
	}

}
