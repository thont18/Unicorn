package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.WorkingSiteRepository;
import com.example.demo.models.ProductType;
import com.example.demo.models.WorkingSite;

@Service
@Transactional
public class WorkingSiteService {
	@Autowired
	private WorkingSiteRepository workingSiteRepository;
	
	public List<WorkingSite> findAll() {
		return workingSiteRepository.findAll();
	}
	
	public Optional<WorkingSite> get(Long id) {
		return workingSiteRepository.findById(id);
	}
	
	public Page<WorkingSite> findAll(Pageable pageable) {
		return workingSiteRepository.findAll(pageable);
	}
	
	public WorkingSite save(WorkingSite workingSite) {
		return workingSiteRepository.save(workingSite);
	}
	
	public void delete(Long id) {
		workingSiteRepository.deleteById(id);
	}
}