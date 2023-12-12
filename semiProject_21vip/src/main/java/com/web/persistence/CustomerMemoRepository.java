package com.web.persistence;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.web.domain.Customer;
import com.web.domain.CustomerMemo;

public interface CustomerMemoRepository extends CrudRepository<CustomerMemo, Long> {



}
