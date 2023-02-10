package com.cafeProject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CafeSearchDto {
	private String searchDateType;
	private String cafeNm;
	private String searchBy;
	private String searchQuery = "";
}
