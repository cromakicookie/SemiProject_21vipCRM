package com.web.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.web.domain.Calendar;

public interface CalendarRepository extends CrudRepository<Calendar, Long>{

	// 'A' 'B' 'C' 'D' 'U' 로 시작하는 타입 찾기 
	public List<Calendar> findByEventTypeStartingWith(String eventType);
	
	// 'U' 가 아닌 모든 것 출력
	@Query("SELECT c FROM Calendar c WHERE SUBSTRING(c.eventType, 1, 1) != 'U'")
	public List<Calendar> findByEventTypeIsNotStartingWith();
	
	// 정확한 eventType=() 출력
	public List<Calendar> findByEventType(String eventType);
	
}
