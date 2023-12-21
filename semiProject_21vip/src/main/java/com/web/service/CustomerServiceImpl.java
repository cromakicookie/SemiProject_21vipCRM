package com.web.service;

import java.util.List;
import java.util.Random;

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
	
	//고객 수정
	@Override
	public void updateCustomer(Customer customer) {
		Customer cus1 = customerRepo.findById(customer.getCustomerNum()).get();
		System.out.println(customer.getCustomerNum());
		cus1.setCustomerNum(customer.getCustomerNum());
		cus1.setCustomerName(customer.getCustomerName());
		cus1.setCustomerGrade(customer.getCustomerGrade());
		cus1.setCustomerBirth(customer.getCustomerBirth());
		cus1.setCustomerGender(customer.getCustomerGender());
		cus1.setCustomerHP(customer.getCustomerHP());
		cus1.setFavoriteStore(customer.getFavoriteStore());
		customerRepo.save(cus1);
		
	}
	
	//고객삭제
	@Override
	public void deleteCustomer(String customerNumber) {
		// TODO Auto-generated method stub
		customerRepo.deleteById(customerNumber);
	}
		
	//고객 상세 정보 조회
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
	
	//메모삭제
	@Override
	public void deleteMemo(Long memoNum) {
		// TODO Auto-generated method stub
		memoRepo.deleteById(memoNum);
		
	}


}
