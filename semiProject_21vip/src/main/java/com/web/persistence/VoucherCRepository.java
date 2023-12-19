package com.web.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.web.domain.Voucher;
import com.web.domain.VoucherC;

public interface VoucherCRepository extends CrudRepository<VoucherC, String> {
	
	public VoucherC findByCheckedVoucherCode(String checkedVoucherCode);


}
