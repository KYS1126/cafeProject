package com.cafeProject.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cafeProject.dto.CafeDto;

@RequestMapping("/cafe")
@Controller
public class CafeController {

	@GetMapping(value="insertcafe")
	public String main(Model model) {
		model.addAttribute("cafeDto", new CafeDto());
		return "/cafe/insertcafe";
	}
	
	@PostMapping(value = "/cafe/insert")
	public String insert (@Valid CafeDto cafeDto, BindingResult bindingResult,
			Model model, @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList) {
		
		if(bindingResult.hasErrors()) {
			return "cafe/insertcafe";
		}
		
		if(itemImgFileList.get(0).isEmpty() && cafeDto.getId() == null) {
			model.addAttribute("errorMessage", "첫 번째 상품 이미지는 필수 입력 값입니다.");
			return "item/itemForm";
		}
		try {
			itemService.saveItem(cafeDto, itemImgFileList);
		} catch(Exception e) {
			model.addAttribute("errorMessage", "상품 등록 중 에러가 발생했습니다.");
			return "item/itemForm";
		}
		
		
		
		return "redirect:/";
	}
	
	
}
