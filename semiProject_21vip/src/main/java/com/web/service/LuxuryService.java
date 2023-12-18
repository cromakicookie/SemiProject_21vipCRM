package com.web.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.domain.Luxury;

public interface LuxuryService {
	
	void insertLuxury(Luxury luxury);
	
//	Page<Luxury> findCustomerNum(String searchKeyword, Pageable pageable);
	
	Page<Luxury> PageList(Pageable pageable);
	
	public Luxury luxuryView(long luxurySeq);
	
	public void luxuryDelete(long luxurySeq);
	
	public void luxuryupdate(Luxury luxury);
	
	Page<Luxury> findBrand(String brand, Pageable pageable);
	
//	Page<Luxury> findCustomerNumBrand(String searchKeyword, Pageable pageable, String brand);
	
	
	
//	public Customer searchCustomer(String num); 
}
