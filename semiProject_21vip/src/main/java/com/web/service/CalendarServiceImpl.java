package com.web.service;

import java.util.List;

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
