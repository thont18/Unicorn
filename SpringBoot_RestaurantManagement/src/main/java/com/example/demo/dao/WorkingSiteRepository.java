package com.example.demo.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.models.WorkingSite;

public interface WorkingSiteRepository extends JpaRepository<WorkingSite, Long> {
	@Query("FROM WorkingSite ws WHERE ws.name LIKE %:searchText% ORDER BY ws.name ASC")
	Page<WorkingSite> findAllSite(Pageable pageable, @Param("searchText") String searchText);
	
	@Query("FROM WorkingSite ws ORDER BY ws.name ASC")
	Page<WorkingSite> findAllSiteAscending(Pageable pageable);
}
