package com.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class staticController {
	
	@GetMapping("/login")
	public void login() {
	}
	
	@GetMapping("/logout")
	public void logout() {
	}

}
