package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.PromotionType;

public interface PromotionRepository extends JpaRepository<PromotionType,Long> {

}
