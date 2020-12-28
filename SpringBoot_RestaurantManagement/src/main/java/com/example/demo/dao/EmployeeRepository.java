package com.example.demo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query("select identityCardNumber from Employee where identityCardNumber = :newIdentityCardNumber")
	public List<String> checkIdentityCardNumber(@RequestParam("identityCardNumber") String newIdentityCardNumber);

<<<<<<< HEAD
	@Query("select e from Employee e where e.firstName LIKE %?1% or e.lastName LIKE %?1%")
=======
	@Query("select e from Employee e where e.firstName LIKE %?1% or e.lastName LIKE %?1% ORDER BY  e.lastName ASC ,  e.firstName ASC")
>>>>>>> 8f4859ad739749e36073266c8243523d11bc0a09
	public Page<Employee> findAllEmployee(Pageable pageable ,@Param("searchName") String searchName);

	@Query("select e from Employee e ORDER BY e.lastName ASC , e.firstName ASC")
	public Page<Employee> findAllEmployeeAscending(Pageable pageable);

<<<<<<< HEAD
	
=======
>>>>>>> 8f4859ad739749e36073266c8243523d11bc0a09
}
