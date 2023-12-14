package com.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.domain.Voucher;
import com.web.domain.VoucherC;
import com.web.persistence.VoucherCRepository;
import com.web.persistence.VoucherRepository;

@Service
public class VoucherServiceImpl implements VoucherService {
	
	//Voucher 와 VoucherC Repo 한번에 관리
	@Autowired
	private VoucherRepository voucherRepo;
	
	@Autowired
	private VoucherCRepository voucherCRepo;
	
	
	//바우처 정보 조회 (voucherCode 로 존재여부)
	@Override
	public Voucher getVoucher(String voucherCode) {
		return voucherRepo.findByVoucherCode(voucherCode);
	}
	
	//고객에게 발급된 바우처 정보 등록 (VoucherC 테이블로)
	@Override
	public void issueVoucher(VoucherC voucherC) {
		// TODO Auto-generated method stub
		voucherCRepo.save(voucherC);
		
	}
	
	
	
}
