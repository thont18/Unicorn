package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ResultResponse;
import com.example.demo.models.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@CrossOrigin
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService empSer;

//	@GetMapping()
//	public List<Employee> listAllEmployee() {
//		return empSer.listAll();
//	}

	@GetMapping("")
	public ResponseEntity<Page<Employee>> getEmployee(int pageNumber, int pageSize, String sortBy, String sortDir) {
		return new ResponseEntity<Page<Employee>>(
				empSer.findAll(PageRequest.of(pageNumber, pageSize,
						sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending())),
				HttpStatus.OK);
	}

	@GetMapping("/search/{searchName}")
	public ResponseEntity<Page<Employee>> searchEmployee(Pageable pageable, @PathVariable String searchName) {
		return new ResponseEntity<>(empSer.listAll(pageable, searchName), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id) {
		return new ResponseEntity<Employee>(this.empSer.get(id), HttpStatus.OK);
	}

	@PutMapping("/editEmployee/{id}")
	public ResultResponse editEmployee(@RequestBody Employee employee, @PathVariable("id") Long id) {
		List<Employee> newEmployee = new ArrayList();
		Employee oldEmployee = empSer.getId(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Employee with id: " + id));
		if (this.empSer.check(employee.getIdentityCardNumber()).isEmpty()) {
			oldEmployee.setIdentityCardNumber(employee.getIdentityCardNumber());
			oldEmployee.setLastName(employee.getLastName());
			oldEmployee.setFirstName(employee.getFirstName());
			oldEmployee.setPhoneNumber(employee.getPhoneNumber());
			oldEmployee.setAddress(employee.getAddress());
			newEmployee.add(empSer.save(oldEmployee));
			return new ResultResponse(0, "Update success", newEmployee);
		} else {
			if (this.empSer.check(employee.getIdentityCardNumber()).contains(employee.getIdentityCardNumber())) {
				System.out.println(this.empSer.check(employee.getIdentityCardNumber()));
				oldEmployee.setIdentityCardNumber(employee.getIdentityCardNumber());
				oldEmployee.setLastName(employee.getLastName());
				oldEmployee.setFirstName(employee.getFirstName());
				oldEmployee.setPhoneNumber(employee.getPhoneNumber());
				oldEmployee.setAddress(employee.getAddress());

				newEmployee.add(empSer.save(oldEmployee));
				return new ResultResponse(0, "Update success with new code Employee", newEmployee);
			} else {
				throw new ResourceNotFoundException("Duplicate code Employee");
			}
		}
	}

	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable("id") Long id) throws Exception {
		empSer.delete(id);
	}

	@PostMapping()
	public ResponseEntity<?> createEmployee(@RequestBody @Validated Employee employee) {
		Employee saveEmployee = empSer.save(employee);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		return new ResponseEntity<>(saveEmployee, responseHeaders, HttpStatus.CREATED);
	}

}
