package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.WorkingSite;

public interface WorkingSiteRepository extends JpaRepository<WorkingSite, Long> {

}
