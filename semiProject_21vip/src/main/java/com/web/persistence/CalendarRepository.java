package com.web.persistence;

import org.springframework.data.repository.CrudRepository;

import com.web.domain.Calendar;

public interface CalendarRepository extends CrudRepository<Calendar, Long>{

}
