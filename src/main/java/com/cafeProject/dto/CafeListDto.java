package com.cafeProject.dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CafeListDto {

	private Long id;
	
	private String cafeNm;
	
	private String cafeDetail;
	
	private String cafeAddress;
	
	private String cafeTel;
	
	private String cafeClose;
	
	private String imgUrl;
	
	@QueryProjection //쿼리 dsl조회시 dto객체로 받아옴
	public CafeListDto(Long id, String cafeNm, String cafeDetail, String cafeaddress,
			String cafeTel, String cafeClose, String imgUrl) {
		this.id = id;
		this.cafeNm = cafeNm;
		this.cafeDetail = cafeDetail;
		this.cafeAddress = cafeaddress;
		this.cafeTel = cafeTel;
		this.cafeClose = cafeClose;
		this.imgUrl = imgUrl;
	}
}
