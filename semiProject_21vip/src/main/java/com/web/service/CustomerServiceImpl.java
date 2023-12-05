package com.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.domain.Customer;
import com.web.persistence.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	//고객 등록
	@Override
	public void insertCustomer(Customer customer) {
		customerRepo.save(customer);
		
	}
	
	//고객 상세 정보
	@Override
	public Customer getCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerRepo.findById(customer.getCustomerNum()).get();
	}
	


}
