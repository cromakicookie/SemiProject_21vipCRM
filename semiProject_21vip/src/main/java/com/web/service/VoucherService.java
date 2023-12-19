package com.web.service;

import java.util.Optional;

import com.web.domain.Voucher;
import com.web.domain.VoucherC;

public interface VoucherService {
	
	//바우처 기본정보
	Voucher getVoucher(String voucherCode);
	
	//발급완료된 바우처 정보
	VoucherC getIssuedVoucher(String voucherCode);
	
	void issueVoucher(VoucherC voucherC); 
	
	void deleteVoucher(String voucherCode);
	

}
