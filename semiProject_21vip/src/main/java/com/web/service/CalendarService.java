package com.web.service;

import java.util.List;
import java.util.Map;

import com.web.domain.Calendar;
import com.web.domain.dataFile;

public interface CalendarService {

	List<Calendar> getCalendarList();

	void insertCalendar(Calendar calendar);

	Calendar getCalendar(Calendar calendar);

	void updateCalendar(Calendar calendar);

	void deleteCalendar(Calendar calendar);

	List<Calendar> getCalendarListOnly(String eventType);

	List<Calendar> getCalendarListNotOnly();

	List<Map<String, Object>> getCalendarListU(String eventType);

	List<Map<String, Object>> getCalendarListAll(String eventType);

	List<Map<String, Object>> getCalendarListA(String eventType);



}
