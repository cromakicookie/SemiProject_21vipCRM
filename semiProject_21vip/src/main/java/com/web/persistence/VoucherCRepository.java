package com.web.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.web.domain.Voucher;
import com.web.domain.VoucherC;

public interface VoucherCRepository extends CrudRepository<VoucherC, String> {



}
