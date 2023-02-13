package com.cafeProject.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DelelteController {
	@DeleteMapping("/cafe/{cafeId}/delete")
	public @ResponseBody ResponseEntity deleteOrder(@PathVariable("orderId") Long orderId, Principal principal) {

		return null;
	}
}
