package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.EmployeeRepository;
import com.example.demo.models.Employee;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository empRepo;

	public List<Employee> listAll() {
		return empRepo.findAll();
	}

	public Employee save(Employee position) {
		return empRepo.save(position);
	}

	public Optional<Employee> getId(Long id) {
		return empRepo.findById(id);
	}

	public Employee get(Long id) {
		return empRepo.findById(id).get();
	}

	public void delete(Long id) {
		empRepo.deleteById(id);
	}

	public List<String> check(@RequestParam("newIdentityCardNumber") String newIdentityCardNumber) {
		return empRepo.checkIdentityCardNumber(newIdentityCardNumber);
	}

	public Page<Employee> listAll(Pageable pageable, String searchName) {
		return empRepo.findAllEmployee(pageable, searchName);
	}

	public Page<Employee> findAll(Pageable pageable) {
		return empRepo.findAll(pageable);
	}

}
