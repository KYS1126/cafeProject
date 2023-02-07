package com.cafeProject.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CafeDto {
	
	private Long id;	//카페 고유 번호
	
	@NotBlank(message = "카페 이름은 필수 입력 값입니다.")
	private String cafeNm;	//카페 이름
	
	@NotEmpty(message = "카페 상세 내용은 필수 입력 값입니다.")
	@Length(min = 1, max = 600, message = "상새 내용은 300글자 내외로 작성 해 주세요")
	private String cafeDetail;	// 카페 상세내용

	@NotBlank(message = "카페 주소는 필수 입력 값입니다.")
	private String cafeAddress;	//카페 주소
	
	@NotBlank(message = "카페 전화번호는 필수 입력 값입니다.")
	private String cafeTel;		//카페 전화번호
	
	@NotBlank(message = "카페 영업시간은 필수 입력 값입니다.")
	private String cafeClose;	//카페 영업시간
	
	@NotBlank(message = "카페 테마는 필수 입력 값입니다.")
	private Long themeId;	//테마 고유 번호


}
