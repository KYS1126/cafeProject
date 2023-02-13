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

import com.cafeProject.dto.CafeImgDto;
import com.cafeProject.dto.CafeListDto;
import com.cafeProject.dto.CafeSearchDto;
import com.cafeProject.dto.InsertCafeDto;
import com.cafeProject.entity.Cafe;
import com.cafeProject.entity.CafeImg;
import com.cafeProject.repository.CafeImgRepository;
import com.cafeProject.repository.CafeRepository;
import com.cafeProject.repository.CafeRepositoryCustom;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CafeService {

	private final CafeImgRepository cafeImgRepository;
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
	
	//카페 상세정보 가져오기
	@Transactional(readOnly = true)
	public InsertCafeDto getCafeDtl(Long cafeId) {
		//1. cafe_img 테이블의 이미지를 가져온다.
		List<CafeImg> cafeImgList = cafeImgRepository.findBycafeIdOrderByIdAsc(cafeId);
		List<CafeImgDto> cafeImgDtoList = new ArrayList<>();
		
		//엔티티 객체 -> dto 객체로 변환
		for (CafeImg cafeImg : cafeImgList) {
			CafeImgDto cafeImgDto = CafeImgDto.of(cafeImg);
			cafeImgDtoList.add(cafeImgDto);
		}
		
		//2. cafe 테이블에 있는 데이터를 가져온다
		Cafe cafe = cafeRepository.findById(cafeId)
				.orElseThrow(EntityNotFoundException :: new);
		
		//엔티티 객체 -> dto 객체로 변환
		InsertCafeDto insertCafeDto = InsertCafeDto.of(cafe);
		
		//상품의 이미지 정보를 넣어주기
		insertCafeDto.setCafeImgDtoList(cafeImgDtoList); 
		
		return insertCafeDto; // 리턴해주기
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
