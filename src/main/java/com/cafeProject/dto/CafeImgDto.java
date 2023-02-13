package com.cafeProject.dto;

import org.modelmapper.ModelMapper;

import com.cafeProject.entity.CafeImg;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CafeImgDto {
	private Long id;
	
	private String imgNm;
	
	private String img_ori;
	
	private String img_Url;
	
	private String repimg_Yn;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public static CafeImgDto of (CafeImg cafeImg) {
		return modelMapper.map(cafeImg, CafeImgDto.class);
	}
	
}
