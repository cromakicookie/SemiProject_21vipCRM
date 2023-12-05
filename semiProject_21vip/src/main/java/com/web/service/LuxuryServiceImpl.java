package com.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.domain.Luxury;
import com.web.persistence.LuxuryRepository;

@Service
public class LuxuryServiceImpl implements LuxuryService {
	
	@Autowired
	private LuxuryRepository luxuryRepo;
	
	@Override
	public void insertLuxury(Luxury luxury) {
		luxuryRepo.save(luxury);
	}
	
	

}
