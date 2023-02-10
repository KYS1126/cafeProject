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

import com.cafeProject.dto.CafeListDto;
import com.cafeProject.dto.CafeSearchDto;
import com.cafeProject.dto.InsertCafeDto;
import com.cafeProject.entity.Cafe;
import com.cafeProject.entity.CafeImg;
import com.cafeProject.repository.CafeRepository;
import com.cafeProject.repository.CafeRepositoryCustom;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CafeService {

	private final CafeRepository cafeRepository;
	private final CafeImgService cafeImgService;
	
	//이미지 등록
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
			
			cafeImgService.saveCafeImg(cafeImg, cafeImgFileList.get(i));
			
		}
		
		return cafe.getId();
	}
	
	@Transactional(readOnly = true)
	public Page<Cafe> getAdminItemPage(CafeSearchDto itemSearchDto, Pageable pageable) {
		return cafeRepository.getAdminCafePage(itemSearchDto, pageable);
	}
	
	@Transactional(readOnly = true)
	public Page<CafeListDto> getMainItemPage(CafeSearchDto itemSearchDto, Pageable pageable) {
		return cafeRepository.getCafeListPage(itemSearchDto, pageable);
	}
	
}
