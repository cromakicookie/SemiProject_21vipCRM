package com.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web.domain.Luxury;
import com.web.persistence.LuxuryPageRepository;
import com.web.persistence.LuxuryRepository;

@Service
public class LuxuryServiceImpl implements LuxuryService {
	
	@Autowired
	private LuxuryRepository luxuryRepo;
	
	@Autowired
	private LuxuryPageRepository luxuryPageRepo;
	
	@Override
	public void insertLuxury(Luxury luxury) {
		luxuryRepo.save(luxury);
	}

	@Override
	public Page<Luxury> PageSearchList(String searchKeyword, Pageable pageable) {
		return luxuryPageRepo.findBycustomerNumContaining(searchKeyword, pageable);
	}

	@Override
	public Page<Luxury> PageList(Pageable pageable) {
		return luxuryPageRepo.findAll(pageable);
	}

	@Override
	public Luxury luxuryView(long luxurySeq) {
		return luxuryRepo.findById(luxurySeq).get();
	}
	
	@Override
	public void luxuryDelete(long luxurySeq) {
		luxuryRepo.deleteById(luxurySeq);
	}

	@Override
	public void luxuryupdate(Luxury luxury) {
		Luxury findluxury = luxuryRepo.findById(luxury.getLuxurySeq()).get();
		
		findluxury.setCustomerNum(luxury.getCustomerNum());
		findluxury.setLuxuryBrandName(luxury.getLuxuryBrandName());
		findluxury.setLuxuryDate(luxury.getLuxuryDate());
		findluxury.setLuxuryName(luxury.getLuxuryName());
		findluxury.setLuxuryPhone(luxury.getLuxuryPhone());
		findluxury.setLuxuryTime(luxury.getLuxuryTime());
		findluxury.setLuxuryHeadCount(luxury.getLuxuryHeadCount());
		luxuryRepo.save(findluxury);
	}

	
	

}
