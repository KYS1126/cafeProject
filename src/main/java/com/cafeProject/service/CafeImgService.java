package com.cafeProject.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cafeProject.repository.CafeImgRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CafeImgService {
	
	@Value("${itemImgLocation}")
	private String itemImgLocation; //C:/cafe/item
	
	private final CafeImgRepository cafeImgRepository;
	
	private final FileService fileService;
}
