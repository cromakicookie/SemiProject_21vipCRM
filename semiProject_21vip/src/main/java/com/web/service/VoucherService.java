package com.web.service;

import com.web.domain.Voucher;
import com.web.domain.VoucherC;

public interface VoucherService {
	
	Voucher getVoucher(String voucherCode);
	
	void issueVoucher(VoucherC voucherC); 

}
