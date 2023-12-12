package com.web.controller;


import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
	
	@GetMapping("calendar")
	public String calendarPage(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> authorityStrings = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // 사용자 role 추가
        model.addAttribute("userAuthorities", authorityStrings);
		return "calendar/calendar";
	}
	
	// [일정관리] admin, manager만 입력가능
	@PostMapping("inputCalendar")
	public String inputCalendar(Calendar cal, MultipartFile uploadFiles) throws IOException{
		if(uploadFiles != null) {
			Long id = fs.uploadFile(uploadFiles);
			dataFile file = fs.getFile(id);
			cal.setFile(file);
			cs.insertCalendar(cal);
		}else {
			cs.insertCalendar(cal);
		}

		return "redirect:calendar";
	}
	
	// [마이페이지] 개인 일정 자유롭게 입력 가능
	@PostMapping("inputCalendarU")
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

        
        
        // 사용자 role 추가
        model.addAttribute("userAuthorities", authorityStrings);
        
        
        Member member = new Member();
        member.setUsername(userId);
        model.addAttribute("member",ms.getMember(member));
        
        System.out.println(userId);
        model.addAttribute("userId",userId);
        return "calendar/mypage";
    }
}
