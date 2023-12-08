package com.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.web.domain.Calendar;
import com.web.persistence.CalendarRepository;

@Service
public class CalendarServiceImpl implements CalendarService{
	
	@Autowired
	private CalendarRepository cr;
	
	@Override
	public List<Calendar> getCalendarList(){
		return (List<Calendar>) cr.findAll();
	}
	
	@Override
	public List<Calendar> getCalendarListOnly(String eventType){
		return cr.findByEventTypeStartingWith(eventType);
	}
	
	@Override
	public List<Calendar> getCalendarListNotOnly(){
		return cr.findByEventTypeIsNotStartingWith();
	}
	
	@Override
	public List<Map<String, Object>> getCalendarListU(String eventType){
		List<Map<String, Object>> objectList = new ArrayList<>();
		Map<String, Object> calendarEvent = new HashMap<>();
		List<Calendar> event = cr.findByEventTypeStartingWith(eventType);
		
		for(Calendar cal : event) {
			calendarEvent.put("title", cal.getTitle());
	        calendarEvent.put("start", cal.getStart());
	        calendarEvent.put("end", cal.getEnd());
	        calendarEvent.put("eventContent", cal.getEventContent());
	        calendarEvent.put("eventType", cal.getEventType());
	        calendarEvent.put("backgroundColor", "yellow");
		}
		objectList.add(calendarEvent);
		return objectList;
	}
	
	@Override
	public List<Map<String, Object>> getCalendarListAll(String eventType){
		List<Map<String, Object>> objectList = new ArrayList<>();
		// 행사 (일정관리)
		List<Calendar> eventAll = cr.findByEventTypeIsNotStartingWith();
		for (Calendar cal : eventAll) {
	        objectList.add(createEventMap(cal, "blue","white"));
	    }
		// 유저 (마이페이지)
		List<Calendar> event = cr.findByEventTypeStartingWith(eventType);
		for (Calendar cal : event) {
	        objectList.add(createEventMap(cal, "yellow","black"));
	    }
		
		return objectList;
	}
	
	private Map<String, Object> createEventMap(Calendar cal, String backgroundColor, String color){
		Map<String, Object> calendarEvent = new HashMap<>();
		calendarEvent.put("id", cal.getId());
		calendarEvent.put("title", cal.getTitle());
		calendarEvent.put("start", cal.getStart());
		calendarEvent.put("end", cal.getEnd());
		calendarEvent.put("eventContent", cal.getEventContent());
		calendarEvent.put("eventType", cal.getEventType());
		calendarEvent.put("backgroundColor", backgroundColor);
		calendarEvent.put("textColor", color);
		return calendarEvent;
	}
	
	@Override
	public void insertCalendar(Calendar calendar) {
		cr.save(calendar);
	}

	@Override
	public Calendar getCalendar(Calendar calendar) {
		Calendar selectCal = cr.findById(calendar.getId()).get();
		return selectCal;
	}
	
	@Override
	public void updateCalendar(Calendar calendar) {
		Calendar selectCal = cr.findById(calendar.getId()).get();
		
		selectCal.setTitle(calendar.getTitle());
		selectCal.setStart(calendar.getStart());
		selectCal.setEnd(calendar.getEnd());
		selectCal.setEventContent(calendar.getEventContent());
		selectCal.setEventType(calendar.getEventType());
		selectCal.setFile(calendar.getFile());
		
		cr.save(selectCal);
	}
	
	@Override
	public void deleteCalendar(Calendar calendar) {
		cr.deleteById(calendar.getId());
	}
	
}
