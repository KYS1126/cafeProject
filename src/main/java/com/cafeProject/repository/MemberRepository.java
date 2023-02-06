package com.cafeProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafeProject.entity.Member;

public interface MemberRepository extends JpaRepository<Member,Long>{
	Member findByEmail(String email);
}
