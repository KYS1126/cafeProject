package com.cafeProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafeProject.entity.CafeImg;


public interface CafeImgRepository extends JpaRepository<CafeImg, Long> {
	List<CafeImg> findBycafeIdOrderByIdAsc(Long cafeId);
}
