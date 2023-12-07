package com.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.web.domain.Calendar;
import com.web.persistence.CalendarRepository;
import com.web.service.CalendarService;


@Controller
public class CalendarController {
	
	@Autowired
	CalendarService cs;
	
	@GetMapping("calendar")
	public String calendarPage() {
		return "calendar/calendar";
	}
	
	@PostMapping("inputCalendar")
	public String inputCalendar(Calendar cal) {
		cs.insertCalendar(cal);
		return "redirect:calendar";
	}

}
