package com.web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web.domain.Member;
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
	
	//고객에게 발급된 바우처에서 정보 조회
	@Override
	public VoucherC getIssuedVoucher(String voucherCode) {
		// TODO Auto-generated method stub
		return voucherCRepo.findByCheckedVoucherCode(voucherCode);
	}
	
	//고객에게 발급된 바우처 정보 등록 (VoucherC 테이블로)
	@Override
	public void issueVoucher(VoucherC voucherC) {
		// TODO Auto-generated method stub
		voucherCRepo.save(voucherC);
	}
	
	
	//바우처 삭제
	@Override
	public void deleteVoucher(String voucherCode) {
		// TODO Auto-generated method stub
		voucherCRepo.deleteById(voucherCode);
	}
	
	//바우처생성
	@Override
	public Voucher createVoucher(Voucher voucher) {
		// TODO Auto-generated method stub
		return voucherRepo.save(voucher);
	}
	
	//발행된 바우처 리스트
	@Override
	public List<Voucher> getVoucherList(Voucher Voucher) {
		// TODO Auto-generated method stub
		return (List<Voucher>)voucherRepo.findAll();
	}
	
	//페이징
	@Override
	public Page<Voucher> PageList(Pageable pageable) {
		// TODO Auto-generated method stub
		return voucherRepo.findAll(pageable);
	}
	
	//검색 - 서비스명
	@Override
	public Page<Voucher> findByVoucherServiceName(String searchKeyword, Pageable pageable) {
		// TODO Auto-generated method stub
		return voucherRepo.findByVoucherServiceNameContaining(searchKeyword, pageable);
	}

	
	
	
}
