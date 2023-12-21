package com.web.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.web.domain.Voucher;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {
	
	public Voucher findByVoucherCode(String code);
	
	public Page<Voucher> findAll(Pageable pageable);
	
	public Page<Voucher> findByVoucherServiceNameContaining(String searchKeyword, Pageable pageable);


}
