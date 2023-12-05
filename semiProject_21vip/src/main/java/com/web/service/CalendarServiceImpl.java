package com.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.web.persistence.CalendarRepository;

@Service
public class CalendarServiceImpl {
	
	@Autowired
	private CalendarRepository cr;
	
	

}
