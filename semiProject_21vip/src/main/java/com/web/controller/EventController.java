package com.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.domain.Calendar;
import com.web.domain.Member;
import com.web.domain.dataFile;
import com.web.service.CalendarService;
import com.web.service.FileService;

@RestController
@RequestMapping("/calendar/event")
@Controller
public class EventController {

	@Autowired
	CalendarService cs;

	@Autowired
	FileService fs;

	// [일정관리] 캘린더 목록 가져오기
	@GetMapping("/all")
	public List<Calendar> getAllEvents() {
		List<Calendar> list = cs.getCalendarListNotOnly();
		return list;
	}

	// [마이페이지] 캘린더 목록 가져오기
	@GetMapping("/user")
	public List<Map<String, Object>> getEvent() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// 사용자의 아이디 가져오기
		String userId = authentication.getName(); // Username

		String eventType = "U" + userId;
		List<Map<String, Object>> allList = cs.getCalendarListAll(eventType);
		return allList;
	}

	// 캘린더 저장
	@PostMapping
	public void createEvent(Calendar calendar) {
		cs.getCalendar(calendar);
	}

	// [일정관리] [마이페이지] 일정 누르면 일정 보여주기
	@GetMapping(value = "/{num}", produces = MediaType.APPLICATION_JSON_VALUE) // 일정 번호 가져와서 화면에 가져오기!
	public Calendar EventView(@PathVariable("num") Long number) {
		System.out.println(number);
		Calendar cal = new Calendar();
		cal.setId(number);
		Calendar selectCal = cs.getCalendar(cal);
		return selectCal;
	}

	@PutMapping
	public void updateEvent(@ModelAttribute Calendar calendar, @RequestParam("uploadFiles") MultipartFile file)
			throws IOException {
		System.out.println(calendar.toString());
		System.out.println("업데이트 확인");

		try {
			Long id = fs.uploadFile(file);
			dataFile files = fs.getFile(id);

			calendar.setFile(files);
			cs.updateCalendar(calendar);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@DeleteMapping("/{num}")
	public void deleteEvent(@PathVariable("num") Long number) {
		System.out.println("삭제 확인");
		Calendar cal = new Calendar();
		cal.setId(number);
		cs.deleteCalendar(cal);
	}

}
