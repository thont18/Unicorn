package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	@Query("select username from Account where username = :newUsername")
		public List<String> checkUserName(@RequestParam("username") String newUsername);
}
