package com.web.persistence;

import org.springframework.data.repository.CrudRepository;

import com.web.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, String> {

}
