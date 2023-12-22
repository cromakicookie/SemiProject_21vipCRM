package com.web.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.web.domain.Customer;
import com.web.domain.CustomerMemo;

public interface CustomerRepository extends CrudRepository<Customer, String> {
	


}
