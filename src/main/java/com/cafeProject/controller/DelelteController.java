package com.cafeProject.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafeProject.service.CafeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DelelteController {
	
	private final CafeService cafeService;
	
	@GetMapping("/cafe/delete/{cafeId}")
    public String deleteOrder(@PathVariable("cafeId") Long cafeId, Principal principal, Model model) {
		cafeService.deleteCafe(cafeId);
		return "redirect:/";
    }
	
}
