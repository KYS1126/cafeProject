package com.cafeProject.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.*;

import org.modelmapper.ModelMapper;

import com.cafeProject.entity.Cafe;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsertCafeDto {
	
	private Long id; //카페 고유 번호
	
	@NotBlank(message = "카페이름은 필수 입력 값입니다.")
	private String cafeNm; //카페 이름

	@NotNull(message = "카페 상세 설명은 필수 값입니다.")
	private String cafeDetail; //카페 상세 설명
	
	@NotNull(message = "카페 주소는 필수 입력 값입니다.")
	private String cafeAddress; //카페 주소
	
	@NotNull(message = "카페 전화번호는 필수 입력 값입니다.")
	private String cafeTel; //카페 전화번호
	
	@NotNull(message = "카페 휴무일은 필수 입력 값입니다.")
	private String cafeClose; //카페 휴무일
	
	//get set 자동 지정
	private static ModelMapper modelMapper = new ModelMapper();
	
	//카페 엔티티랑 dto 자동 매핑
	public Cafe createCafe () {
		return modelMapper.map(this, Cafe.class);
	}
	
	public static InsertCafeDto of (Cafe cafe) {
		return modelMapper.map(cafe, InsertCafeDto.class);
	}
	
}
