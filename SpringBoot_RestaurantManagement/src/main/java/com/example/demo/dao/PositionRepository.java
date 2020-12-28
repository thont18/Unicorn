package com.example.demo.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.demo.models.Position;

public interface PositionRepository extends JpaRepository<Position, Long>{
	@Query("select p from Position p where p.name LIKE %?1%")
	public Page<Position> findAllPosition(Pageable pageable ,@Param("searchName") String searchName);

	@Query("select p from Position p ORDER BY p.name ASC")
	public Page<Position> findAllPositionAscending(Pageable pageable);

}
