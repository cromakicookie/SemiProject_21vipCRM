package com.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.domain.Customer;
import com.web.domain.CustomerMemo;
import com.web.persistence.CustomerMemoRepository;
import com.web.persistence.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private CustomerMemoRepository memoRepo;
	
	//고객 등록
	@Override
	public void insertCustomer(Customer customer) {
		customerRepo.save(customer);
		
	}
	
	//고객 상세 정보 가져오기
	@Override
	public Customer getCustomer(Customer customer) {
		// TODO Auto-generated method stub
		try {
			return customerRepo.findById(customer.getCustomerNum()).get();
		} catch (Exception e) {
			return null;
		}	
	}
	
	//메모 등록
	@Override
	public void insertMemo(CustomerMemo memo) {
		// TODO Auto-generated method stub
		memoRepo.save(memo);		
	}
	


}
