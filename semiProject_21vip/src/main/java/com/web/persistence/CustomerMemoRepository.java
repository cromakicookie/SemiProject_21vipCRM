package com.web.persistence;

import org.springframework.data.repository.CrudRepository;

import com.web.domain.Customer;
import com.web.domain.CustomerMemo;

public interface CustomerMemoRepository extends CrudRepository<CustomerMemo, Long> {

}
