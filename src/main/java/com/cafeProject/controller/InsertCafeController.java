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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cafeProject.dto.CafeListDto;
import com.cafeProject.dto.CafeSearchDto;
import com.cafeProject.dto.InsertCafeDto;
import com.cafeProject.service.CafeService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/cafe")
@Controller
@RequiredArgsConstructor
public class InsertCafeController {
	
	private final CafeService cafeService;

	//관리자용 카페 등록 페이지를 보여줌
	@GetMapping(value = "insertcafe")
	public String cafeForm(Model model) {
		model.addAttribute("insertCafeDto", new InsertCafeDto());
		return "/insert/insertcafe";
	}
	
	//카페 등록하기
	@PostMapping(value = "new")
	public String cafeNew(@Valid InsertCafeDto insertCafeDto, BindingResult bindingResult,
			Model model, @RequestParam("itemImgFile") List<MultipartFile> cafeImgFileList) {
		
		if(bindingResult.hasErrors()) {
			return "/insert/insertcafe";
		}
		
		if(cafeImgFileList.get(0).isEmpty() && insertCafeDto.getId() == null) {
			model.addAttribute("errorMessage", "첫 번째 카페 이미지는 필수 입력 값입니다.");
			return "insert/insertcafe";
		}
		
		try {
			cafeService.saveCafe(insertCafeDto, cafeImgFileList);
			System.out.println(insertCafeDto);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "카페 등록 중 에러가 발생했습니다.");
			return "insert/insertcafe";
		}
		
		return "redirect:/";
	}
	
	//카페 리스트 페이지 보여주기
	@GetMapping(value = "list")
	public String viewForm(CafeSearchDto cafeSearchDto, Optional<Integer> page, Model model) {
		//Optional -> 값이 null이여도 오류를 발생시키지 않는 객체
		//isPresent() -> boolean 타입 Optional 타입에 값이 있으면 true 없으면 false
		Pageable pafeable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);
		
		Page<CafeListDto> cafes = cafeService.getMainItemPage(cafeSearchDto, pafeable);
		
		model.addAttribute("cafes", cafes);
		model.addAttribute("cafeSearchDto", cafeSearchDto);
		model.addAttribute("maxPage", 5);
		
		return "/cafelist/cafelist";
	}
	
	//관리자용 카페 등록 페이지를 보여줌
	@GetMapping(value = "cafeDetail")
	public String cafeDetail() {
		return "/cafelist/cafeDetail";
	}

	
}
