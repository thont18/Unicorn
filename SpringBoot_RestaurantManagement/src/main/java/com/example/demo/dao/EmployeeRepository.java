package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query("select identityCardNumber from Employee where identityCardNumber = :newIdentityCardNumber")
	public List<String> checkIdentityCardNumber(@RequestParam("identityCardNumber") String newIdentityCardNumber);

	@Query("select e from Employee e where e.firstName LIKE %?1% or e.lastName LIKE %?1% ")
	public List<Employee> search(String name);

}
