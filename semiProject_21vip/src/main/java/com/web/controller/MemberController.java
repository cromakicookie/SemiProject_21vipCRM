package com.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.web.domain.Member;
import com.web.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;

	@GetMapping("/memberMain")
	public String memberMain(Model model) {
		List<Member> memberList = memberService.getMemberList();
		model.addAttribute("memberList", memberList);
		return "member/memberMain";
	}
}
