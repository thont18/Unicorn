package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AccountRepository;
import com.example.demo.models.Account;

@Service
@Transactional
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;
	
	public List<Account> findAll() {
		return accountRepository.findAll();
	}
	
	public Optional<Account> get(Long id) {
		return accountRepository.findById(id);
	}
	
	public Account save(Account account) {
		return accountRepository.save(account);
	}
	
	public void delete(Long id) {
		accountRepository.deleteById(id);
	}
}
