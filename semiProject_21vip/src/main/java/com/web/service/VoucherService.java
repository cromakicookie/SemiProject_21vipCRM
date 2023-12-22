package com.web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.domain.Voucher;
import com.web.domain.VoucherC;

public interface VoucherService {
	
	//바우처 기본정보
	Voucher getVoucher(String voucherCode);
	
	//발급완료된 바우처 정보
	VoucherC getIssuedVoucher(String voucherCode);
	
	void issueVoucher(VoucherC voucherC); 
	
	void deleteVoucher(String voucherCode);
	
	//바우처 생성
	Voucher createVoucher(Voucher voucher);
	
	//발행된 바우처 리스트
	List<Voucher> getVoucherList(Voucher Voucher);

	//페이징
	Page<Voucher> PageList(Pageable pageable);
	
	//검색 - 서비스명
	Page<Voucher> findByVoucherServiceName(String searchKeyword, Pageable pageable);

	//검색 - 일련번호명
	Page<Voucher> findByVoucherCode(String searchKeyword, Pageable pageable);


	

}
