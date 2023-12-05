package com.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalendarController {
	
	@GetMapping("calendar")
	public String calendarPage() {
		return "calendar/calendar";
	}
	
	@GetMapping("mypage")
	public String myPage() {
		return "calendar/mypage";
	}

}
