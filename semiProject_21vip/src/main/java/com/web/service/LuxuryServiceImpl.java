package com.web.service;

import java.util.List;

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
	
	
	// 데이터 추가
	@Override
	public void insertLuxury(Luxury luxury) {
		luxuryRepo.save(luxury);
	}

	// 목록 상세보기
	@Override
	public Luxury luxuryView(long luxurySeq) {
		return luxuryRepo.findById(luxurySeq).get();
	}
	
	// 목록 삭제
	@Override
	public void luxuryDelete(long luxurySeq) {
		luxuryRepo.deleteById(luxurySeq);
	}
	
	// 목록 수정
	@Override
	public void luxuryupdate(Luxury luxury) {
		Luxury findluxury = luxuryRepo.findById(luxury.getLuxurySeq()).get();
		
//		findluxury.setCustomerNum(luxury.getCustomer().getCustomerNum());
		findluxury.setLuxuryBrandName(luxury.getLuxuryBrandName());
		findluxury.setLuxuryDate(luxury.getLuxuryDate());
//		findluxury.setLuxuryName(luxury.getLuxuryName());
		findluxury.setLuxuryPhone(luxury.getLuxuryPhone());
		findluxury.setLuxuryTime(luxury.getLuxuryTime());
		findluxury.setLuxuryHeadCount(luxury.getLuxuryHeadCount());
		luxuryRepo.save(findluxury);
	}
	
	// searchKeyword가 포함되어있는 데이터만 검색
//	@Override
//	public Page<Luxury> findCustomerNum(String searchKeyword, Pageable pageable) {
//		return luxuryPageRepo.findByCustomerNumContaining(searchKeyword, pageable);
//	}
	
	// 전체목록 
	@Override
	public Page<Luxury> PageList(Pageable pageable) {
		return luxuryPageRepo.findAll(pageable);
	}

	@Override
	public Page<Luxury> findBrand(String brand, Pageable pageable) {
		
		return luxuryPageRepo.findByluxuryBrandNameEquals(brand, pageable);
	}

//	@Override
//	public Page<Luxury> findCustomerNumBrand(String searchKeyword, Pageable pageable, String brand) {
//		
//		return luxuryPageRepo.findByCustomerNumContainingAndLuxuryBrandNameEquals(searchKeyword, pageable, brand);
//	}

//	@Override
//	public Customer searchCustomer(String num) {
//		try {
//			Customer searchCustomer = customerRepo.findById(num).get();
//			return searchCustomer;
//		} catch (Exception e) {
//			return null;
//		}
//		
//	}
	

	
	
	
	

}
