package com.cafeProject.controller;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafeProject.dto.MemberDto;
import com.cafeProject.entity.Member;
import com.cafeProject.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/member")
@Controller
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;
	
	//회원가입 html 보내주기
	@GetMapping(value = "/signup")
	public String signup (Model model) {
		model.addAttribute("memberDto", new MemberDto());
		return "member/signup";
	}
	
	//로그인 html 보내주기
	@GetMapping(value = "/signin")
	public String signin () {
		return "member/signin";
	}
	
	@PostMapping(value = "/new")
	public String memberSingUp(@Valid MemberDto memberDto, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "member/signup";
		}
		
		try {
			Member member = Member.createMember(memberDto, passwordEncoder);
			memberService.saveMember(member);
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "member/signup";
		}
		
		return "redirect:/";
	}
	
	//세션 매니저 생성
	private final SessionManager sessionManager;
	
	@GetMapping(value = "/login/error")
	public String loginError(Model model) {
		model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
		return "member/signin";
	}
	
}
