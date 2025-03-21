package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

import com.example.demo.models.Position;
import com.example.demo.service.PositionService;

@RestController
@CrossOrigin
@RequestMapping("/positions")
public class PositionController {

	@Autowired
	private PositionService posSer;

	@GetMapping("/getAllPositions")
	public List<Position> listPosition() {
		return (List<Position>) posSer.listAll();
	}

	@GetMapping("")
	public ResponseEntity<Page<Position>> getPosition(int pageNumber, int pageSize, String sortBy, String sortDir) {
		return new ResponseEntity<Page<Position>>(
				posSer.findAll(PageRequest.of(pageNumber, pageSize,
						sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending())),
				HttpStatus.OK);
	}

	@GetMapping("/search/{searchName}")
	public ResponseEntity<Page<Position>> searchPosition(Pageable pageable, @PathVariable String searchName) {
		return new ResponseEntity<>(posSer.listAll(pageable, searchName), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public Position getPosition(@PathVariable("id") Long id) {
		return posSer.get(id).get();
	}

	@PutMapping("/{id}")
	public Position updatePosition(@PathVariable("id") Long id, @RequestBody Position position, BindingResult resuit) {
		if (resuit.hasErrors()) {
			throw new IllegalArgumentException("Invalid Position data");
		}
		posSer.get(id);
		return posSer.save(position);
	}

	@DeleteMapping("/{id}")
	public void deletePosition(@PathVariable("id") Long id) throws Exception {
		posSer.delete(id);
	}

	@PostMapping("")
	public ResponseEntity<?> createPosition(@RequestBody @Validated Position position) {
		Position savePosition = posSer.save(position);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		return new ResponseEntity<>(savePosition, responseHeaders, HttpStatus.CREATED);
	}

}
