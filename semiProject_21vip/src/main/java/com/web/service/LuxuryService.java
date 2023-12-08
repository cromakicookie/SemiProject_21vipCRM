package com.web.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.domain.Luxury;

public interface LuxuryService {
	
	void insertLuxury(Luxury luxury);
	
	Page<Luxury> PageSearchList(String searchKeyword, Pageable pageable);
	
	Page<Luxury> PageList(Pageable pageable);
	
	public Luxury luxuryView(long luxurySeq);
	
	public void luxuryDelete(long luxurySeq);
	
	public void luxuryupdate(Luxury luxury);
	
}
