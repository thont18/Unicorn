package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.demo.exception.ResourceNotFoundExeption;
import com.example.demo.models.Account;
import com.example.demo.service.AccountService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/accounts")
public class AccountController {
	@Autowired
	private AccountService accountService;

	@GetMapping
	public List<Account> getAllAccounts() {
		return this.accountService.findAll();
	}

	@GetMapping("/{id}")
	public Account getAccount(@PathVariable("id") Long id) {
		return this.accountService.get(id).orElseThrow(() -> new ResourceNotFoundExeption("Account ID: " + id + " NOT FOUND"));
	}

	@PostMapping
	public Account createAccount(@RequestBody Account account) {
		return accountService.save(account);
	}

	@PutMapping("/{id}")
	public Account updateAccount(@RequestBody @Validated Account account, @PathVariable("id") Long id) {
		accountService.get(id).orElseThrow(() -> new ResourceNotFoundExeption("Account ID: " + id + " NOT FOUND"));
		return accountService.save(account);
	}

	@DeleteMapping("/{id}")
	public void deleteAccount(@PathVariable("id") Long id) {
		Account account = accountService.get(id).orElseThrow(() -> new ResourceNotFoundExeption("Account ID: " + id + " NOT FOUND"));
		accountService.delete(account.getId());
	}

}