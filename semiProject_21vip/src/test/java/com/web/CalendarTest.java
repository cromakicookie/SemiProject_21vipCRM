package com.web;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.web.controller.CalendarController;
import com.web.controller.EventController;
import com.web.domain.Calendar;
import com.web.persistence.CalendarRepository;

@SpringBootTest
public class CalendarTest {
	
//	@Autowired
//	private Calendar cal;

	@Autowired
	private CalendarRepository cr;
	
	@Autowired
	private EventController ec;
	
//	@Test
//	public void CalTest() {
//		Calendar cal = new Calendar();
//		cal.setEventType("창립기념");
//		cal.setTitle("행사날");
//		cal.setEventContent("블라블라");
//		cr.save(cal);
//		System.out.println(cal.toString());
//	}

	
//	@Test
//	public void CalUpdateTest() {
//		Calendar cal = cr.findById(6L).get();
//		cal.setTitle("테스트");
//		ec.updateEvent(cal);
//	}
//	@Test
//	public void CaldeleteTest() {
//		ec.deleteEvent(4L);
//	}
}
