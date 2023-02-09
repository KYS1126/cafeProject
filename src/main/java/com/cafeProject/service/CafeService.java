package com.cafeProject.service;

import java.util.List;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cafeProject.dto.InsertCafeDto;
import com.cafeProject.entity.Cafe;
import com.cafeProject.entity.CafeImg;
import com.cafeProject.repository.CafeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CafeService {

	private final CafeRepository cafeRepository;
	
	public Long saveCafe(InsertCafeDto insertCafeDto, List<MultipartFile> cafeImgFileList) throws Exception{
		
		Cafe cafe = insertCafeDto.createCafe();
		cafeRepository.save(cafe);
		
		for(int i=0; i < cafeImgFileList.size(); i++) {
			CafeImg cafeImg = new CafeImg();
			cafeImg.setCafe(cafe);
			
			if(i == 0) {
				cafeImg.setRepimgYn("Y");
			} else {
				cafeImg.setRepimgYn("N");
			}
		}
		
		return null;
	}
}
