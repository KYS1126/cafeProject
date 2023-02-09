package com.cafeProject.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cafeProject.dto.InsertCafeDto;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class InsertCafeController {
	

	//관리자용 카페 등록 페이지를 보여줌
	@GetMapping(value = "/admin/insertcafe")
	public String cafeForm(Model model) {
		model.addAttribute("insertCafeDto", model);
		return "/insert/insertcafe";
	}
	
	//카페 등록하기
	@PostMapping(value = "/admin/insertcafe/new")
	public String cafeNew(@Valid InsertCafeDto insertCafeDto, BindingResult bindingResult,
			Model model, @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList) {
		
		if(bindingResult.hasErrors()) {
			return "/insert/insertcafe";
		}
		
		try {
			
		} catch (Exception e) {

		}
		
		return null;
	}
	
}
