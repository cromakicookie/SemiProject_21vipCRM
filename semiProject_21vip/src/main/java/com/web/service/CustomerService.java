package com.web.service;

import java.util.List;

import com.web.domain.Customer;
import com.web.domain.CustomerMemo;

public interface CustomerService {
	
	void insertCustomer(Customer customer);
	
	void deleteCustomer(String customerNumber);
	
	void updateCustomer(Customer customer);
	
	Customer getCustomer(Customer customer);
	
	void insertMemo(CustomerMemo memo);
	
	void deleteMemo(Long memoNum);
	
	

}
