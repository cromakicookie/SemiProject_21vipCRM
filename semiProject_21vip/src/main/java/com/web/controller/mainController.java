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

//@SessionAttributes("member")
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
	
//	@GetMapping("login")
//	public void login() {
//		
//	}
	
//	@PostMapping("/login")
//	public String login(Member member, Model model) {
//		Member findMember = mainService.getMember(member);
//		if(findMember != null && findMember.getPassword().equals(member.getPassword())) {
//			model.addAttribute("member", findMember);
//			System.out.println(findMember.getUsername());
//			return "redirect:/";
//		}
//		return "redirect:loginForm";
//	}
	
	@GetMapping("/loginSuccess")
	public void loginSuccess() {
		
	}
	
	@GetMapping("/loginError")
	public String loginError() {
		return "main/loginError";
	}
	
	@GetMapping("/accessDeneind")
	public String accessDenied() {
		return "main/accessDeneind";
	}
	

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
	
//	@PostMapping("/findId")
//	public ResponseEntity<?> findId(Member member, Model model) {
//		List<String> findId = mainService.findId(member);
//		System.out.println(findId.toString());
//		if(findId.toString() == null) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Your error message");
//		}
//		model.addAttribute("findId", findId);
//		return ResponseEntity.ok("Success");
//	}
	
	@PostMapping("/findPw")
	public String findPw(Member member) {
		System.out.println(member);
		boolean res = mainService.findPw(member);
		return "/main/findPwResult";
//		if(res) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("일치하는 값이 없습니다");
//		}
//		return ResponseEntity.ok("Success");
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
	
	@PostMapping("/idValid")
	@ResponseBody
	public boolean idValid(@RequestParam("username") String username) {
		boolean haveId = mainService.idValid(username);
		return haveId;
	}
	
//	
//	@GetMapping("employee")
//	public String employee(){
//		return "admin/employeeManagement";
//	}
	
	
	

	
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
	
	// 인증번호 보내기 페이지
//		@GetMapping("/find/password/auth")
//		public String auth(String username, HttpSession session) {
//		    Map<String, Object> authStatus = (Map<String, Object>) session.getAttribute("authStatus");
//		    if(authStatus == null || !username.equals(authStatus.get("username"))) {
//		        return "redirect:/find/password";
//		    }
//		    
//		    return "user/find/auth";
//		}

}
