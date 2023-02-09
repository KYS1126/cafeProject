package com.cafeProject.service;

import javax.transaction.Transactional;

import org.apache.groovy.parser.antlr4.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cafeProject.entity.CafeImg;
import com.cafeProject.repository.CafeImgRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CafeImgService {
	
	@Value("${itemImgLocation}")
	private String itemImgLocation; //C:/cafe/item
	
	private final CafeImgRepository cafeImgRepository;
	
	private final FileService fileService;//
	
	public void saveCafeImg(CafeImg cafeImg, MultipartFile cafeImgFile) throws Exception {
		String oriImgNmae = cafeImgFile.getOriginalFilename();
		String imgName = "";
		String imgUrl = "";

		if(!StringUtils.isEmpty(oriImgNmae)) {
			imgName = fileService.uploadFile(itemImgLocation, oriImgNmae, cafeImgFile.getBytes());
			imgUrl = "/images/item/" + imgName;
		}
		
		//이미지 저장
		cafeImg.updateCageImg(oriImgNmae, imgName, imgUrl);
		cafeImgRepository.save(cafeImg);
		
	}
	
	
	
}
