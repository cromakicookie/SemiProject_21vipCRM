package com.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.config.MemberExcelExporter;
import com.web.domain.Member;
import com.web.domain.OAuthToken;
import com.web.domain.Role;
import com.web.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	private HttpServletRequest request;

	//사원리스트 불러오기 & 조건 검색
	@GetMapping("/memberMain")
	public String findByOption(@PageableDefault(size=4) Pageable pageable, 
			@RequestParam(name="memberDept", defaultValue="") String memberDept, @RequestParam(name="memberRole", defaultValue="") Role memberRole, @RequestParam(name="memberName", defaultValue="") String memberName,
			Model model) {
		Page<Member> paging = memberService.findMember(pageable, memberDept, memberRole, memberName);
		model.addAttribute("paging", paging);
		
		// 현재 실행 중인 서버의 절대 경로 얻기
        String serverAbsolutePath = request.getRequestURL().toString();
        String contextPath = request.getContextPath();
        
        // 서버의 루트 경로로 설정
        String absolutePath = serverAbsolutePath.substring(0, serverAbsolutePath.indexOf(contextPath) + contextPath.length());

        // 모델에 추가
        model.addAttribute("absolutePath", absolutePath);
        
		
		return "member/memberMain";
	}
	
	//사원정보 수정
	@PostMapping("member/updateMember")
	@ResponseBody
	public void updateMember(@RequestParam("username") String username, @RequestParam("memberEmail") String memberEmail
							, @RequestParam("memberBirth") String memberBirth,  @RequestParam("memberDept") String memberDept, @RequestParam("memberRole") Role memberRole) {
		
		Member member = memberService.findMember(username);
		member.setMemberBirth(memberBirth);
		member.setMemberEmail(memberEmail);
		member.setMemberDept(memberDept);
		member.setMemberRole(memberRole);
		memberService.updateMember(member);
	}
	
	//사원 삭제
	@GetMapping("/deleteMember")
	public String deleteMember(@RequestParam("username") String username) {
		System.out.println(username);
		memberService.deleteMember(username);
		return "redirect:/memberMain";
	}

	//사원 이름으로 검색
	@GetMapping("/searchMember")
	public String searchMember(@PageableDefault(size=5, sort="memberName", direction =Sort.Direction.ASC) Pageable pageable, @RequestParam("memberName") String memberName, Model model) {
		Page<Member> paging = memberService.PageList(pageable);
		
		model.addAttribute("paging", paging);
		
		return "member/memberMain";
	}
	
	
	//------엑셀 다운로드
	@GetMapping("/excel/download")
	public void memberExcelDownLoad(HttpServletResponse response, 
			@RequestParam(name="memberName", defaultValue="") String memberName,
			@RequestParam(name="memberDept", defaultValue="") String memberDept,
			@RequestParam(name="memberRole", defaultValue="") Role memberRole
			) throws IOException {
		System.out.println("엑셀 다운로드 요청");
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=memberList.xlsx";
		
		response.setHeader(headerKey, headerValue);
		
		List<Member> members = memberService.excelDownloadMember(memberName, memberDept, memberRole);
		
		System.out.println(members);
		
		MemberExcelExporter excelExporter = new MemberExcelExporter(members);
		excelExporter.export(response);
		
	}
}
