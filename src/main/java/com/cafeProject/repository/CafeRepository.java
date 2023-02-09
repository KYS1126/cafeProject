package com.cafeProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.cafeProject.entity.Cafe;

public interface CafeRepository extends JpaRepository<Cafe, Long>,
	QuerydslPredicateExecutor<Cafe>{

	List<Cafe> findBycafeNm(String cafeNm);
	
	
}
