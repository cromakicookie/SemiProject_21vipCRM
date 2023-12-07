package com.web.service;

import java.util.List;

import com.web.domain.Calendar;

public interface CalendarService {

	List<Calendar> getCalendarList();

	void insertCalendar(Calendar calendar);

	Calendar getCalendar(Calendar calendar);

	void updateCalendar(Calendar calendar);

	void deleteCalendar(Calendar calendar);

}
