package com.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.web.domain.Member;
import com.web.service.MainService;

@SessionAttributes("member")
@Controller
public class mainController {
	
	
	@Autowired
	private MainService mainService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@GetMapping("/")
	public String index(HttpSession session, Model model) {
		 // 현재 사용자의 Authentication 객체 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

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
	
//	@GetMapping("login")
//	public void login() {
//		
//	}
	
	@PostMapping("/login")
	public String login(Member member, Model model) {
		Member findMember = mainService.getMember(member);
		if(findMember != null && findMember.getPassword().equals(member.getPassword())) {
			model.addAttribute("member", findMember);
			System.out.println(findMember.getUsername());
			return "redirect:/";
		}
		return "redirect:loginForm";
	}
	
	@GetMapping("/loginSuccess")
	public void loginSuccess() {
		
	}
	
	@GetMapping("/accessDeneind")
	public String accessDenied() {
		return "main/accessDeneind";
	}
	
//	@GetMapping("/loginSuccess")
//	public String loginSuccess() {
//		return "redirect:/";
//	}
//	
	@GetMapping("/logout")
	public void logout() {}
	
	@GetMapping("/logoutSuccess")
	public String logoutSuccess() {
		return "main/logoutSuccess";
	}
	
	@GetMapping("findIdPw")
	public String findIdPw() {
		return "main/findIdPw";
	}
	
	@GetMapping("joinForm")
	public String joinForm() {
		return "main/joinForm";
	}

	@PostMapping("/join")
	public String join(Member member) {
		String encPassword = bCryptPasswordEncoder.encode(member.getPassword());
		member.setPassword(encPassword);
		mainService.insertMember(member);
		return "main/registerSuccess";
	}
	
	
	@GetMapping("employee")
	public String employee(){
		return "admin/employeeManagement";
	}
	
	@GetMapping("mypage")
	public String mypage() {
		return "calendar/mypage";
	}
	
//	@GetMapping("mypageIdCheck")
//	public String mypageIdCheck(@ModelAttribute("user") Member member, Model model) {
//		if(member.getUsername() !=null) {
//			return "redirect:mypage";
//		}else {
//			return "redirect:loginForm";
//		}
//	}
	
//	@ModelAttribute("member")
//	public Member setEmptyMember() {
//		return new Member();
//	}
	
//	@GetMapping("mypage")
//    public String myPage(Model model, @ModelAttribute("member") Member Member) {
//		Member findMember = mainService.getMember(Member);
//        model.addAttribute("Member",findMember);
//        return "calendar/mypage";
//    }

}
