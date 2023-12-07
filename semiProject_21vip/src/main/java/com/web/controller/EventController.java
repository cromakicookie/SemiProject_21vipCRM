package com.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.domain.Calendar;
import com.web.persistence.CalendarRepository;
import com.web.service.CalendarService;

@RestController
@RequestMapping("/calendar/event")
@Controller
public class EventController {
	
	@Autowired
	CalendarService cs;
	
	// 캘린더 목록 가져오기
	@GetMapping
	public List<Calendar> getAllEvents(Model model){
		List<Calendar> list = cs.getCalendarList();
		model.addAttribute("list",list);
		return list;
	}

	
	// 캘린더 저장
	@PostMapping
	public void createEvent(Calendar calendar){
		cs.getCalendar(calendar);
	}
	
	// 일정 누르면 왼쪽에 일정 보여주기 예정
	@GetMapping(value="/{num}", produces = MediaType.APPLICATION_JSON_VALUE) // 일정 번호 가져와서 화면에 가져오기! 
	public Calendar EventView(@PathVariable("num") Long number){
		System.out.println(number);
		Calendar cal = new Calendar();
		cal.setId(number);
		Calendar selectCal = cs.getCalendar(cal);
		return selectCal;
	}
	
	@PutMapping
	public void updateEvent(@RequestBody Calendar calendar){
		cs.updateCalendar(calendar);
	}

}
