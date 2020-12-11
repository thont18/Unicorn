package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
