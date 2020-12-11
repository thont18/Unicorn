package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

	public Employee get(Long id) {
		return empRepo.findById(id).get();
	}

	public void delete(Long id) {
		empRepo.deleteById(id);
	}

}
