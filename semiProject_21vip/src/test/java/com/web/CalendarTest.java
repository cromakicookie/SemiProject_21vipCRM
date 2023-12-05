package com.web;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.web.domain.Calendar;
import com.web.persistence.CalendarRepository;

@SpringBootTest
public class CalendarTest {
	
//	@Autowired
//	private Calendar cal;

	@Autowired
	private CalendarRepository cr;
	
	@Test
	public void CalTest() {
		Calendar cal = new Calendar();
		cal.setEventType("창립기념");
		cal.setTitle("행사날");
		cal.setStart(new Date());
		cal.setEnd(new Date());
		cal.setEventContent("블라블라");
		cr.save(cal);
		System.out.println(cal.toString());
	}

}
