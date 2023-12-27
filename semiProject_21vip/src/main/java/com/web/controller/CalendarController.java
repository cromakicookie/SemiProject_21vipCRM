package com.web.controller;


import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.web.domain.Calendar;
import com.web.domain.Member;
import com.web.domain.dataFile;
import com.web.service.CalendarService;
import com.web.service.FileService;
import com.web.service.MainService;


@Controller
public class CalendarController {
	
	@Autowired
	CalendarService cs;
	@Autowired
	FileService fs;
	@Autowired
	MainService ms;
	@Autowired
	private HttpServletRequest request;
	
	@GetMapping("calendar")
	public String calendarPage(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> authorityStrings = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // 현재 실행 중인 서버의 절대 경로 얻기
        String serverAbsolutePath = request.getRequestURL().toString();
        String contextPath = request.getContextPath();
        
        // 서버의 루트 경로로 설정
        String absolutePath = serverAbsolutePath.substring(0, serverAbsolutePath.indexOf(contextPath) + contextPath.length());

        // 모델에 추가
        model.addAttribute("absolutePath", absolutePath);
        // 사용자 role 추가
        model.addAttribute("userAuthorities", authorityStrings);
		return "calendar/calendar";
	}
	
	// [일정관리] admin, manager만 입력가능
	@PostMapping("/inputCalendar")
	public String inputCalendar(Calendar cal, @RequestParam("uploadFiles")MultipartFile uploadFiles) throws IOException{
		System.out.println("input");
		try {
			if(uploadFiles != null && !uploadFiles.isEmpty()) {
				Long id = fs.uploadFile(uploadFiles);
				System.out.println(id+"upload");
				dataFile file = fs.getFile(id);
				
				cal.setFile(file);
			}
			cs.insertCalendar(cal);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:calendar";
	}
	
	// [마이페이지] 개인 일정 자유롭게 입력 가능
	@PostMapping("/inputCalendarU")
	public String inputCalendarU(Calendar cal) {
		cs.insertCalendar(cal);
		return "redirect:mypage";
	}

	
	@GetMapping("mypage")
    public String myPage(Model model) { // SecurityContextHolder에서 Authentication 객체 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 사용자의 아이디 가져오기
        String userId = authentication.getName(); // Username
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> authorityStrings = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

     // 현재 실행 중인 서버의 절대 경로 얻기
        String serverAbsolutePath = request.getRequestURL().toString();
        String contextPath = request.getContextPath();
        
        // 서버의 루트 경로로 설정
        String absolutePath = serverAbsolutePath.substring(0, serverAbsolutePath.indexOf(contextPath) + contextPath.length());

        // 모델에 추가
        model.addAttribute("absolutePath", absolutePath);
        // 사용자 role 추가
        model.addAttribute("userAuthorities", authorityStrings.get(0));
        
        
        Member member = new Member();
        member.setUsername(userId);
        model.addAttribute("member",ms.getMember(member));
        
        System.out.println(userId);
        model.addAttribute("userId",userId);
        return "calendar/mypage";
    }
}
