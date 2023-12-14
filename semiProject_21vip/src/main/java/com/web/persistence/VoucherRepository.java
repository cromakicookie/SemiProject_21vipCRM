package com.web.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.web.domain.Voucher;

public interface VoucherRepository extends CrudRepository<Voucher, Long> {
	
	public Voucher findByVoucherCode(String code);

	
}
