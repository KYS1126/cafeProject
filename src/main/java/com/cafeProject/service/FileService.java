package com.cafeProject.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.extern.java.Log;

@Service
@Log
public class FileService {
	
	public String uploadFile(String uploadPath, String originalFileName, byte[] fileData) throws Exception {
		
		UUID uuid = UUID.randomUUID();
		
		String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); //확장자명
		String savedFileName = uuid.toString() + extension; //파일 이름 생성
		String fileUploadFullUrl = uploadPath + "/" + savedFileName;
		
		FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
		fos.write(fileData);
		fos.close();
		
		
		return savedFileName;
	}
	
	//파일 삭제
	public void deleteFile(String filePath)throws Exception {
		File deleteFile = new File (filePath); //파일이 저장된 경로를 이용해서 파일 객체를 생성
	
	if (deleteFile.exists()) { //해당 경로에 파일이 있으면 true 리턴
		deleteFile.delete(); //파일 삭제
		log.info("파일을 삭제하였습니다");
	} else {
		log.info("파일이 존재하지 않습니다.");
	}
	
	
	}

}
