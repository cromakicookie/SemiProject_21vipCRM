package com.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.web.domain.User;
import com.web.service.MainService;

@SessionAttributes("user")
@Controller
public class mainController {
	
	@Autowired
	private MainService mainService;
	
	@GetMapping("/")
	public String index() {
		return "main/index";
	}
	
	@GetMapping("login")
	public String login() {
		return "main/login";
	}
	
	@PostMapping("/login")
	public String login(User user, Model model) {
		User findUser = mainService.getUser(user);
		if(findUser != null && findUser.getUserPw().equals(user.getUserPw())) {
			model.addAttribute("user", findUser);
			return "redirect:/";
		}
		return "redirect:login";
	}
	
	@GetMapping("logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "main/logout";
	}
	
	@GetMapping("findIdPw")
	public String findIdPw() {
		return "main/findIdPw";
	}
	
	@GetMapping("userRegister")
	public String userRegister() {
		return "main/userRegister";
	}
	
	@PostMapping("userRegister")
	public String insertUser(User user) {
		mainService.insertUser(user);
		return "main/registerSuccess";
	}
	
	@GetMapping("employee")
	public String employee(){
		return "admin/employeeManagement";
	}

}
