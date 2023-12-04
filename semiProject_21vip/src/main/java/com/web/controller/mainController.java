package com.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class mainController {
	
	@GetMapping("/")
	public String index() {
		return "main/index";
	}
	
	@GetMapping("login")
	public String login() {
		return "main/login";
	}
	
	@GetMapping("logout")
	public String logout() {
		return "main/logout";
	}
	
	@GetMapping("findIdPw")
	public String findIdPw() {
		return "main/findIdPw";
	}
	
	@GetMapping("register")
	public String register() {
		return "main/register";
	}
	
	@GetMapping("employee")
	public String employee(){
		return "admin/employeeManagement";
	}

}
