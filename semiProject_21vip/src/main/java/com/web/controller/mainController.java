package com.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.web.domain.Member;
import com.web.service.MainService;

import net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition.Undefined;

@Controller
public class mainController {
	
	@Autowired
	private MainService mainService;
	
	
	@GetMapping("/")
	public String index(HttpSession session, Model model) {
		 // 현재 사용자의 Authentication 객체 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        // 사용자의 권한 정보 가져오기
        model.addAttribute("roles", authentication.getAuthorities());
		model.addAttribute("username", authentication.getName());
		System.out.println(authentication.getName());
		return "main/index";
	}
	
	@GetMapping("loginForm")
	public String loginForm() {
		return "main/loginForm";
	}
	
	
	@GetMapping("loginSuccess")
	public void loginSuccess() {
		
	}
	
	@GetMapping("loginError")
	public String loginError() {
		return "main/loginError";
	}
	
	@GetMapping("accessDeneind")
	public String accessDenied() {
		return "main/accessDeneind";
	}
	

	@GetMapping("logout")
	public String logout() {
		return "redirect:/";
	}
	
	@GetMapping("/logoutSuccess")
	public String logoutSuccess() {
		return "main/logoutSuccess";
	}
	
	@GetMapping("findIdPw")
	public String findIdPw() {
		return "main/findIdPw";
	}
	
	@GetMapping("/findIdResult")
	public String findIdResult() {
		return "main/findIdResult";
	}
	
	@PostMapping("/findId")
	public String findId(Member member, Model model) {
		List<String> findId = mainService.findId(member);
		model.addAttribute("findId", findId);
		return "main/findIdResult";
	}
	
	
	@GetMapping("joinForm")
	public String joinForm() {
		return "main/joinForm";
	}

	@PostMapping("/join")
	public String join(Member member) {
		mainService.insertMember(member);
		return "main/registerSuccess";
	}
	
	//id 중복검사
	@PostMapping("/idValid")
	@ResponseBody
	public boolean idValid(@RequestParam("username") String username) {
		boolean haveId = mainService.idValid(username);
		return haveId;
	}
	

}
