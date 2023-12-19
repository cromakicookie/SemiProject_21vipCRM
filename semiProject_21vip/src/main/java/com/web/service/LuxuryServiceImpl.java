package com.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web.domain.Customer;
import com.web.domain.Luxury;
import com.web.persistence.CustomerRepository;
import com.web.persistence.LuxuryRepository;

@Service
public class LuxuryServiceImpl implements LuxuryService {

	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private CustomerService cs;

//	@Autowired
//	private LuxuryRepository luxuryRepo;
	
	@Autowired
	private LuxuryRepository luxuryRepo;
	
	// 전체목록 
	@Override
	public Page<Luxury> PageList(Pageable pageable) {
		return luxuryRepo.findAll(pageable);
	}
	
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
	@Override
	public Page<Luxury> findCustomerNum(String searchKeyword, Pageable pageable) {
		return luxuryRepo.findByCustomerCustomerNumContaining(searchKeyword, pageable);
	}
	


	@Override
	public Page<Luxury> findBrand(String brand, Pageable pageable) {
		
		return luxuryRepo.findByluxuryBrandNameEquals(brand, pageable);
	}

	@Override
	public Page<Luxury> findCustomerNumBrand(String searchKeyword, Pageable pageable, String brand) {
		
		return luxuryRepo.findByCustomerCustomerNumContainingAndLuxuryBrandName(searchKeyword, pageable, brand);
	}

	@Override
	public Map<String, Object> searchCustomer(String customerNum) {
		try {
			Customer customer = new Customer();
			customer.setCustomerNum(customerNum); // customer에 고객번호에 검색한 고객번호를 넣기
			customer = cs.getCustomer(customer);  // findByID를 이용하여 고객번호에 맞는 다른 데이터값을 customer 객체에 넣기
			Map<String, Object> customer1 = new HashMap<>(); // Map을 만들어서 필요한 데이터들 put으로 넣어주기
			customer1.put("customerNum", customer.getCustomerNum()); 
			customer1.put("customerName", customer.getCustomerName());
			customer1.put("customerGrade", customer.getCustomerGrade());
			customer1.put("customerBirth", customer.getCustomerBirth());
			customer1.put("favoriteStore", customer.getFavoriteStore());
			return customer1;
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public Customer getCustomer(String customerNum) {
		Customer customer = new Customer();
		customer.setCustomerNum(customerNum);
		return customerRepo.findById(customer.getCustomerNum()).get();
	}




	

	
	
	
	

}
