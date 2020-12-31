package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import com.example.demo.models.WorkingSite;
import com.example.demo.service.WorkingSiteService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/sites")
public class WorkingSiteController {
	@Autowired
	private WorkingSiteService workingSiteService;

<<<<<<< HEAD
	@GetMapping
	public ResponseEntity<Page<WorkingSite>> getWorkingSites(int pageNumber, int pageSize, String sortBy,
			String sortDir) {
		return new ResponseEntity<Page<WorkingSite>>(
				workingSiteService.findAll(PageRequest.of(pageNumber, pageSize,
						sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending())),
				HttpStatus.OK);
=======
	@GetMapping("/getAllSites")
	public List<WorkingSite> getAllWorkingSites() {
		return this.workingSiteService.findAll();
>>>>>>> 717a06e9612174598764008560ed6cefee22f001
	}

	@GetMapping("/{id}")
	public WorkingSite getWorkingSite(@PathVariable("id") Long id) {
		return this.workingSiteService.get(id)
				.orElseThrow(() -> new ResourceNotFoundException("Site ID: " + id + " NOT FOUND"));
	}

	@PostMapping
	public WorkingSite createWorkingSite(@RequestBody WorkingSite workingSite) {
		return workingSiteService.save(workingSite);
	}

	@PutMapping("/{id}")
	public WorkingSite updateWorkingSite(@RequestBody @Validated WorkingSite workingSite, @PathVariable("id") Long id) {
		workingSiteService.get(id)
				.orElseThrow(() -> new ResourceNotFoundException("Site ID: " + id + " NOT FOUND"));
		return workingSiteService.save(workingSite);
	}

	@DeleteMapping("/{id}")
	public void deleteWorkingSite(@PathVariable("id") Long id) {
		WorkingSite workingSite = workingSiteService.get(id)
				.orElseThrow(() -> new ResourceNotFoundException("Site ID: " + id + " NOT FOUND"));
		workingSiteService.delete(workingSite.getId());
	}
}