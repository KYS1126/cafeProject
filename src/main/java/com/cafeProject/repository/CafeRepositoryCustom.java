package com.cafeProject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cafeProject.dto.CafeListDto;
import com.cafeProject.dto.CafeSearchDto;
import com.cafeProject.entity.Cafe;

//사용자 정의 인터페이스 작성
public interface CafeRepositoryCustom {
	
	//상품관리 페이지 아이템을 가져온다.
	Page<Cafe> getAdminCafePage(CafeSearchDto itemSearchDto, Pageable pageable);
	
	//카페 리스트에 뿌리는 아이템을 가져온다.
	Page<CafeListDto> getCafeListPage(CafeSearchDto itemSearchDto, Pageable pageable);
	
}
