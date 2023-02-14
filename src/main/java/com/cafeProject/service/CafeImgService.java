package com.cafeProject.service;

import javax.persistence.EntityNotFoundException;
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
	
	@Value("${cafeImgLocation}")
	private String cafeImgLocation; //C:/cafe/item
	
	private final CafeImgRepository cafeImgRepository;
	
	private final FileService fileService;//
	
	public void saveCafeImg(CafeImg cafeImg, MultipartFile cafeImgFile) throws Exception {
		String oriImgNmae = cafeImgFile.getOriginalFilename();
		String imgName = "";
		String imgUrl = "";

		if(!StringUtils.isEmpty(oriImgNmae)) {
			imgName = fileService.uploadFile(cafeImgLocation, oriImgNmae, cafeImgFile.getBytes());
			imgUrl = "/images/item/" + imgName;
		}
		
		//이미지 저장
		cafeImg.updateCageImg(oriImgNmae, imgName, imgUrl);
		cafeImgRepository.save(cafeImg);
		
	}
	
	public void updateCafeImg (Long cafeImgId, MultipartFile cafeImgFile) throws Exception{
		
		if(!cafeImgFile.isEmpty()) { //파일이 있으면
			CafeImg savedCafeImg = cafeImgRepository.findById(cafeImgId)
					.orElseThrow(EntityNotFoundException::new);
			
			
			if(!StringUtils.isEmpty(savedCafeImg.getImgNm())) {//savedItemImg에서 getImgName이 있으면
				fileService.deleteFile(cafeImgLocation + "/" + savedCafeImg);
				
			}
			
			//이미지 파일 업로드
			String oriImgName = cafeImgFile.getOriginalFilename();
			String imgName = fileService.uploadFile(cafeImgLocation, oriImgName, cafeImgFile.getBytes());
			String imgUrl = "/images/item/" + imgName;
			
			savedCafeImg.updateCageImg(oriImgName, imgName, imgUrl);
		}
		
	}
	
	
}
