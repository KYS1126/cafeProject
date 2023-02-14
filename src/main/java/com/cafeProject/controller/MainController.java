package com.cafeProject.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cafeProject.dto.CafeListDto;
import com.cafeProject.dto.CafeSearchDto;
import com.cafeProject.service.CafeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final CafeService cafeService;

	@GetMapping(value="/")
	public String main(CafeSearchDto cafeSearchDto, Optional<Integer> page, Model model) {
		
		Pageable pafeable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);
		
		Page<CafeListDto> cafes = cafeService.getMainItemPage(cafeSearchDto, pafeable);
		
		model.addAttribute("cafes", cafes);
		model.addAttribute("cafeSearchDto", cafeSearchDto);
		model.addAttribute("maxPage", 5);
		
		
		
		
		return "main";
	}
	
}
