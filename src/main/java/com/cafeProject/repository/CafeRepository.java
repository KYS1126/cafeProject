package com.cafeProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.cafeProject.entity.Cafe;

public interface CafeRepository extends JpaRepository<Cafe, Long>,
	QuerydslPredicateExecutor<Cafe>{

	/*
	 * List<Cafe> findByItemNm(String cafeNm);
	 * 
	 * //select * from item where item_nm = ? or item_detail = ? List<Cafe>
	 * findByItemNmOrItemDetail(String cafeNm, String cafeDetail);
	 */
	
	
}
